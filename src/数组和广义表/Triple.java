package 数组和广义表;

import 接口.Addible;

public class Triple implements Comparable<Triple>,Addible<Triple>
{
	public int row;
	public int column;
	public int value;
	
	public Triple(int row,int column,int value)
	{
		if(row>=0&&column>=0)
		{
			this.row=row;
			this.column=column;
			this.value=value;
		}
		else throw new IllegalArgumentException("行、列号不能为负数：row"+row+", column"+column);
	}
	public Triple(Triple tri)
	{
		this(tri.row,tri.column	, tri.value);
	}
	public String toString()
	{
		return "("+row+","+column+","+value+")";
	}
	public boolean equals(Object obj)
	{
		if (this==obj)
            return true;
        if (!(obj instanceof Triple))
            return false;
        Triple tri = (Triple)obj;
        return this.row==tri.row && this.column==tri.column && this.value==tri.value;
	}
	@Override
	public int compareTo(Triple tri)
	{
		if(this.row==tri.row&&this.column==tri.column)
			return 0;
		return (this.row<tri.row||this.row==tri.row&&this.column<tri.column)?-1:1;
	}
	@Override
	public void add(Triple term)
	{
		if(this.compareTo(term)==0)
		{
			this.value+=term.value;
		}
		else 
			throw new IllegalArgumentException("两项的指数不同，不能相加。");
	}
	@Override
	public boolean removable()	//约定删除元素的条件，实现Addible<T>接口
	{
		return this.value==0;
	}
	public Triple toSymmetry()	//返回矩阵对称位置元素的三元组
	{
		return new Triple(this.column, this.row, this.value);
	}
}
