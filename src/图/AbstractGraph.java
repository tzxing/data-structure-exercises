package 图;

import 数组和广义表.Triple;
import 栈和队列.LinkedQueue;
import 线性表.SeqList;
import 线性表.SinglyList;

public abstract class AbstractGraph<T>
{

	protected static final int MAX_WEIGHT = 0x0000ffff;
	protected SeqList<T> vertexlist; // 顶点顺序表，储存图的顶点集合

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
		return "顶点集合：" + this.vertexlist.toString() + "\n";
	}

	public T getVertex(int i)
	{
		return this.vertexlist.get(i);
	}

	public void setVertex(int i, T x)
	{
		this.vertexlist.set(i, x);
	}

	public void DFSTraverse(int i)// 非连通图的一次深度优先搜索遍历，从顶点vi出发
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

	// 从顶点vi出发的一次深度优先搜索，遍历一个连通分量；visited指定访问标记数组。递归算法
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

	public void BFSTraverse(int i) // 非连通图的一次广度优先搜索遍历，从顶点vi出发
	{
		boolean[] visited = new boolean[this.vertexCount()]; // 访问标记数组
		int j = i;
		do
		{
			if (!visited[j]) // 若顶点vj未被访问
			{
				System.out.print("{ ");
				breadthfs(j, visited); // 从vj出发的一次广度优先搜索
				System.out.print("} ");
			}
			j = (j + 1) % this.vertexCount(); // 在其他连通分量中寻找未被访问顶点
		}
		while (j != i);
		System.out.println();
	}

	// 从顶点vi出发的一次广度优先搜索，遍历一个连通分量，使用队列
	private void breadthfs(int i, boolean[] visited)
	{
		System.out.print(this.getVertex(i) + " "); // 访问顶点vi
		visited[i] = true; // 设置访问标记
		// SeqQueue<Integer> que = new SeqQueue<Integer>(this.vertexCount());
		// //创建顺序队列
		LinkedQueue<Integer> que = new LinkedQueue<Integer>(); // 创建链式队列
		que.add(i); // 访问过的顶点vi序号入队，自动转换成Integer(i))
		while (!que.isEmpty()) // 当队列不空时循环
		{
			i = que.poll(); // 出队，自动转换成int;
			for (int j = next(i, -1); j != -1; j = next(i, j)) // j依次获得vi的所有邻接顶点
				if (!visited[j]) // 若顶点vj未访问过
				{
					System.out.print(this.getVertex(j) + " ");// 访问顶点vj
					visited[j] = true;
					que.add(j); // 访问过的顶点vj序号入队
					// System.out.println("顶点队列："+que.toString());
				}
		}
	}

	// Prim算法 构造带权无向图的最小生成树，输出最小生成树的各边及代价
	public void minSpanTree()
	{
		Triple[] mst = new Triple[vertexCount() - 1];
		for (int i = 0; i < mst.length; i++)
			mst[i] = new Triple(0, i + 1, this.weight(0, i + 1));
		for (int i = 0; i < mst.length; i++)
		{
			// 找到最小权值的边
			int minweight = mst[i].value, min = i;
			for (int j = i + 1; j < mst.length; j++)
				if (mst[j].value < minweight)
				{
					minweight = mst[j].value;
					min = j;
				}
			// 将权值最小的边和第i个边交换
			Triple edge = mst[min];// edge为当前权值最小的边
			mst[min] = mst[i];
			mst[i] = edge;

			int tv = edge.column;
			for (int j = i + 1; j < mst.length; j++)
			{
				int v = mst[j].column;// 因为mst中column是唯一的，所以比较column
				int weight = this.weight(tv, v);
				if (weight < mst[j].value)
					mst[j] = new Triple(tv, v, weight);
			}
		}
		System.out.print("\n最小生成树的边集合：");
		int mincost = 0;
		for (int i = 0; i < mst.length; i++)
		{
			System.out.print(mst[i] + " ");
			mincost += mst[i].value;
		}
		System.out.println("，最小代价为" + mincost);
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
			int mindist = MAX_WEIGHT, min = 0; // 路径长度最小值及下标
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
					dist[k] = dist[min] + this.weight(min, k); // 用更短路径替换
					path[k] = min; // 最短路径经过min顶点
				}
			}
		}
		System.out.print(this.getVertex(i) + "顶点的单源最短路径");
		for (int j = 0; j < n; j++)
		{
			if (j != i)
			{
				SinglyList<T> pathlink = new SinglyList<>();
				pathlink.insert(0, this.getVertex(j));
				for (int k = path[j]; k != i && k != j && k != -1; k = path[k])
				{
					pathlink.insert(0, this.getVertex(k));// 反序头插入得到正序
				}
				pathlink.insert(0, this.getVertex(i));
				System.out.print(pathlink.toString() + "长度" + (dist[j] == MAX_WEIGHT ? "∞" : dist[j] + ","));
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
