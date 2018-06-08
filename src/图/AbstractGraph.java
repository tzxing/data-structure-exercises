package ͼ;

import ����͹����.Triple;
import ջ�Ͷ���.LinkedQueue;
import ���Ա�.SeqList;
import ���Ա�.SinglyList;

public abstract class AbstractGraph<T>
{

	protected static final int MAX_WEIGHT = 0x0000ffff;
	protected SeqList<T> vertexlist; // ����˳�������ͼ�Ķ��㼯��

	public AbstractGraph(int length)
	{
		this.vertexlist = new SeqList<T>(length);
	}

	public AbstractGraph()
	{
		this(10);
	}

	public int vertexCount()
	{
		return this.vertexlist.size();
	}

	public String toString()
	{
		return "���㼯�ϣ�" + this.vertexlist.toString() + "\n";
	}

	public T getVertex(int i)
	{
		return this.vertexlist.get(i);
	}

	public void setVertex(int i, T x)
	{
		this.vertexlist.set(i, x);
	}

	public void DFSTraverse(int i)// ����ͨͼ��һ��������������������Ӷ���vi����
	{
		boolean[] visited = new boolean[this.vertexCount()];
		int j = i;
		do
		{
			if (!visited[j])
			{
				System.out.print("{");
				this.depthfs(j, visited);
				System.out.print("}");
			}
			j = (j + 1) % this.vertexCount();
		}
		while (j != i);
		System.out.println();
	}

	// �Ӷ���vi������һ�������������������һ����ͨ������visitedָ�����ʱ�����顣�ݹ��㷨
	private void depthfs(int i, boolean[] visited)
	{
		System.out.println(this.getVertex(i) + " ");
		visited[i] = true;
		int j = this.next(i, -1);
		while (j != -1)
		{
			if (!visited[j])
				depthfs(j, visited);
			j = this.next(i, j);
		}
	}

	public void BFSTraverse(int i) // ����ͨͼ��һ�ι�����������������Ӷ���vi����
	{
		boolean[] visited = new boolean[this.vertexCount()]; // ���ʱ������
		int j = i;
		do
		{
			if (!visited[j]) // ������vjδ������
			{
				System.out.print("{ ");
				breadthfs(j, visited); // ��vj������һ�ι����������
				System.out.print("} ");
			}
			j = (j + 1) % this.vertexCount(); // ��������ͨ������Ѱ��δ�����ʶ���
		}
		while (j != i);
		System.out.println();
	}

	// �Ӷ���vi������һ�ι����������������һ����ͨ������ʹ�ö���
	private void breadthfs(int i, boolean[] visited)
	{
		System.out.print(this.getVertex(i) + " "); // ���ʶ���vi
		visited[i] = true; // ���÷��ʱ��
		// SeqQueue<Integer> que = new SeqQueue<Integer>(this.vertexCount());
		// //����˳�����
		LinkedQueue<Integer> que = new LinkedQueue<Integer>(); // ������ʽ����
		que.add(i); // ���ʹ��Ķ���vi�����ӣ��Զ�ת����Integer(i))
		while (!que.isEmpty()) // �����в���ʱѭ��
		{
			i = que.poll(); // ���ӣ��Զ�ת����int;
			for (int j = next(i, -1); j != -1; j = next(i, j)) // j���λ��vi�������ڽӶ���
				if (!visited[j]) // ������vjδ���ʹ�
				{
					System.out.print(this.getVertex(j) + " ");// ���ʶ���vj
					visited[j] = true;
					que.add(j); // ���ʹ��Ķ���vj������
					// System.out.println("������У�"+que.toString());
				}
		}
	}

	// Prim�㷨 �����Ȩ����ͼ����С�������������С�������ĸ��߼�����
	public void minSpanTree()
	{
		Triple[] mst = new Triple[vertexCount() - 1];
		for (int i = 0; i < mst.length; i++)
			mst[i] = new Triple(0, i + 1, this.weight(0, i + 1));
		for (int i = 0; i < mst.length; i++)
		{
			// �ҵ���СȨֵ�ı�
			int minweight = mst[i].value, min = i;
			for (int j = i + 1; j < mst.length; j++)
				if (mst[j].value < minweight)
				{
					minweight = mst[j].value;
					min = j;
				}
			// ��Ȩֵ��С�ıߺ͵�i���߽���
			Triple edge = mst[min];// edgeΪ��ǰȨֵ��С�ı�
			mst[min] = mst[i];
			mst[i] = edge;

			int tv = edge.column;
			for (int j = i + 1; j < mst.length; j++)
			{
				int v = mst[j].column;// ��Ϊmst��column��Ψһ�ģ����ԱȽ�column
				int weight = this.weight(tv, v);
				if (weight < mst[j].value)
					mst[j] = new Triple(tv, v, weight);
			}
		}
		System.out.print("\n��С�������ı߼��ϣ�");
		int mincost = 0;
		for (int i = 0; i < mst.length; i++)
		{
			System.out.print(mst[i] + " ");
			mincost += mst[i].value;
		}
		System.out.println("����С����Ϊ" + mincost);
	}

	public void shortestPath(int i)
	{
		int n = this.vertexCount();
		boolean[] vset = new boolean[n];
		vset[i] = true;
		int[] dist = new int[n];
		int[] path = new int[n];
		for (int j = 0; j < n; j++)
		{
			dist[j] = this.weight(i, j);
			path[j] = (j != i && dist[j] < MAX_WEIGHT) ? i : -1;
		}
		for (int j = (i + 1) % n; j != i; j = (j + 1) % n) // j??????????
		{
			int mindist = MAX_WEIGHT, min = 0; // ·��������Сֵ���±�
			for (int k = 0; k < n; k++)
			{
				if (!vset[k] && dist[k] < mindist)
				{
					mindist = dist[k];
					min = k;
				}
			}
			if (mindist == MAX_WEIGHT)
				break;
			vset[min] = true;
			for (int k = 0; k < n; k++)
			{
				if (!vset[k] && this.weight(min, k) < MAX_WEIGHT && dist[min] + this.weight(min, k) < dist[k])
				{
					dist[k] = dist[min] + this.weight(min, k); // �ø���·���滻
					path[k] = min; // ���·������min����
				}
			}
		}
		System.out.print(this.getVertex(i) + "����ĵ�Դ���·��");
		for (int j = 0; j < n; j++)
		{
			if (j != i)
			{
				SinglyList<T> pathlink = new SinglyList<>();
				pathlink.insert(0, this.getVertex(j));
				for (int k = path[j]; k != i && k != j && k != -1; k = path[k])
				{
					pathlink.insert(0, this.getVertex(k));// ����ͷ����õ�����
				}
				pathlink.insert(0, this.getVertex(i));
				System.out.print(pathlink.toString() + "����" + (dist[j] == MAX_WEIGHT ? "��" : dist[j] + ","));
			}

		}
		System.out.println();

	}

	public abstract int insertVertex(T x);

	public abstract void removeVertex(int i);

	public abstract int weight(int i, int j);

	// public abstract void insertEdge(int i,int j,int weight);
	protected abstract int next(int i, int j);

}
