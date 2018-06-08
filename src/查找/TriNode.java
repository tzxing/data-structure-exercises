package 查找;

public class TriNode<T>
{
	public T data;
	public TriNode<T> parent ,left, right;
	
	public TriNode(T data, TriNode<T> parent, TriNode<T> left, TriNode<T> right)
    {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
    public TriNode(T data)                       //构造指定值的叶子结点
    {
        this(data, null, null, null);
    }
    public TriNode()
    {
        this(null, null, null, null);
    }
    
    public String toString()
    {
        return this.data.toString();
    }
    public boolean isLeaf()                      //判断是否叶子结点
    {
        return this.left==null && this.right==null;
    }

}
