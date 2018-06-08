package 图;

import 数组和广义表.Matrix;
import 数组和广义表.Triple;

public class MatrixGraph<T> extends AbstractGraph<T>
{
	protected Matrix matrix;

	public MatrixGraph(int length)
	{
		super(length);
		this.matrix = new Matrix(length);
	}

	public MatrixGraph()
	{
		this(10);
	}

	public MatrixGraph(T[] vertices)
	{
		this(vertices.length);
		for (int i = 0; i < vertices.length; i++)
		{
			this.insertVertex(vertices[i]);
		}

	}

	public MatrixGraph(T[] vertices, Triple[] edges)
	{
		this(vertices);
		for (int j = 0; j < edges.length; j++)
		{
			this.insertEdge(edges[j]);
		}
	}

	public String toString()
	{
		String str = super.toString() + "邻接矩阵：\n";
		int n = this.vertexCount();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (this.matrix.get(i, j) == MAX_WEIGHT)
					str += "     ∞";
				else
					str += String.format("%6d", this.matrix.get(i, j));
			}
			str += "\n";
		}
		return str;
	}

	@Override
	public int insertVertex(T x) // 插入元素为x的顶点，返回x顶点序号
	{
		int i = this.vertexlist.insert(x); // 顶点顺序表尾插入x，返回x序号，自动扩容
		if (i >= this.matrix.getRows()) // 若邻接矩阵容量不够，
			this.matrix.setRowsColumns(i + 1, i + 1); // 矩阵扩容。保持邻接矩阵行列数同图的顶点数
		for (int j = 0; j < i; j++) // 初始化第i行、列元素值为∞。i==j值已为0
		{
			this.matrix.set(i, j, MAX_WEIGHT);
			this.matrix.set(j, i, MAX_WEIGHT);
		}
		return i;
	}

	@Override
	public void removeVertex(int i)
	{
		int n = this.vertexCount();
		if (i >= 0 && i < n)
		{
			this.vertexlist.remove(i);
			for (int j = i + 1; j < n; j++)
			{
				for (int k = 0; k < n; k++)
				{
					this.matrix.set(j - 1, k, this.matrix.get(j, k));
				}
			}
			for (int j = 0; j < n; j++)
			{
				for (int k = i + 1; k < n; k++)
				{
					this.matrix.set(j, k - 1, this.matrix.get(j, k));
				}
			}
			this.matrix.setRowsColumns(n - 1, n - 1);
		}
		else
			throw new IndexOutOfBoundsException("i=" + i);
	}

	public void insertEdge(int i, int j, int weight)
	{
		if (i != j)
		{
			if (weight <= 0 || weight > MAX_WEIGHT)
				weight = MAX_WEIGHT;
			this.matrix.set(i, j, weight);
		}
		else
			throw new IllegalArgumentException("不能插入自身环，i=" + i + "j=" + j);
	}

	public void insertEdge(Triple edge)
	{
		this.insertEdge(edge.row, edge.column, edge.value);
	}

	public void removeEdge(int i, int j)
	{
		if (i != j)
			this.matrix.set(i, j, MAX_WEIGHT);
	}

	public void removeEdge(Triple edge)
	{
		this.removeEdge(edge.row, edge.column);
	}

	@Override
	public int weight(int i, int j)
	{
		return this.matrix.get(i, j);
	}

	@Override
	// 返回顶点vi在vj后的后继邻接顶点序号 ；若j=-1，返回vi的第一个邻接顶点序号；若不存在后继邻接顶点，返回-1
	protected int next(int i, int j)
	{
		int n = this.vertexCount();
		if (i >= 0 && i < n && j >= -1 && j < n && i != j)
			for (int k = j + 1; k < n; k++) // 当j=-1时，k从0开始寻找后继邻接顶点
				if (this.matrix.get(i, k) > 0 && this.matrix.get(i, k) < MAX_WEIGHT)// 权值表示有边
					return k;
		return -1;
	}

	public int degree(int i)
	{
		int num = 0;
		for (int j = 0; j < matrix.columns; j++)
			if (this.matrix.get(j, i) > 0 && this.matrix.get(j, i) < MAX_WEIGHT)
				num++;
		for (int j = 0; j < matrix.columns; j++)
			if (this.matrix.get(i, j) > 0 && this.matrix.get(i, j) < MAX_WEIGHT)
				num++;
		return num;

	}

	public static void main(String[] args)
	{
		String[] xStrings = { "A", "B", "C", "D" };
		Triple[] xTriples = { new Triple(0, 1, 1), new Triple(0, 2, 2), new Triple(0, 3, 3), new Triple(1, 3, 1) };
		MatrixGraph<String> matrixGraph = new MatrixGraph<>(xStrings, xTriples);
		matrixGraph.shortestPath(1);
		System.out.println(matrixGraph.degree(2));// 实验函数测试
	}

}
