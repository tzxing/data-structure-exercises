package 树和二叉树;

public class BinaryNode<T>
{
	public T data;
	public BinaryNode<T> left,right;
	
	public BinaryNode(T data,BinaryNode<T> left,BinaryNode<T> right)
	{
		this.data=data;
		this.left=left;
		this.right=right;
	}
	public BinaryNode(T data)
	{
		this(data, null, null);
	}
	public BinaryNode()
	{
		this(null);
	}
	public String toString()
	{
		return this.data.toString();
	}
	public boolean isLeaf()
	{
		return this.left==null&&this.right==null;
	}
	public BinaryNode<T> copy(BinaryNode<T> src)//返回src的深拷贝
	{
		BinaryNode<T> p=new BinaryNode<>();
		p.data=src.data;
		if(src.left!=null)
		{
			p.left=copy(src.left);
		}
		if(src.right!=null)
		{
			p.right=copy(src.right);
		}
		
		return p;
	}

	
	public boolean exactlyEquals(BinaryNode<T> p)//若以this和p为根的树相等，返回true
	{
		boolean isEqual=true;
		if(!this.data.equals(p.data))
			isEqual=false;
		if(p.left!=null&&this.left!=null)
		{
			isEqual=this.left.exactlyEquals(p.left);//this和p左子树均不为空，递归调用比较左子树是否相等
		}
		else if(p.left==null&&this.left!=null||p.left==null&&this.left!=null)
		{
			isEqual=false;
		}
		if(p.right!=null&&this.right!=null)
		{
			isEqual=this.right.exactlyEquals(p.right);//this和p右子树均不为空，递归调用比较右子树是否相等
		}
		else if(p.right==null&&this.right!=null||p.right!=null&&this.right==null)
		{
			isEqual=false;
		}
		return isEqual;
			
	}
	
	
	public BinaryNode<T> search (BinaryTree<T> pattern)//返回以this为根的树里前根次序遍历第一个匹配pattern的子树的根节点
	{
		BinaryNode<T> leftRet,rightRet;//不直接返回避免左子树直接返回null导致逻辑错误
		if(this.exactlyEquals(pattern.root))//this匹配返回this
			return this;
		if(this.left!=null)
		{
			leftRet=this.left.search(pattern);//递归调用，this的左子树是不是pattern
		}
		else
			leftRet=null;
		if(this.right!=null)
		{
			rightRet=this.right.search(pattern);//递归调用，this的右子树是不是pattern
		}
		else
			rightRet=null;
		if(leftRet!=null)
			return leftRet;//左子树匹配则返回左子树根节点
		else if (rightRet!=null) 
		{
			return rightRet;//右子树匹配则返回右子树根节点
		}
		else 
		{
			return null;//都不匹配返回空
		}
		
	}
	


}
