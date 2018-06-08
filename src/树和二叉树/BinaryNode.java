package ���Ͷ�����;

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
	public BinaryNode<T> copy(BinaryNode<T> src)//����src�����
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

	
	public boolean exactlyEquals(BinaryNode<T> p)//����this��pΪ��������ȣ�����true
	{
		boolean isEqual=true;
		if(!this.data.equals(p.data))
			isEqual=false;
		if(p.left!=null&&this.left!=null)
		{
			isEqual=this.left.exactlyEquals(p.left);//this��p����������Ϊ�գ��ݹ���ñȽ��������Ƿ����
		}
		else if(p.left==null&&this.left!=null||p.left==null&&this.left!=null)
		{
			isEqual=false;
		}
		if(p.right!=null&&this.right!=null)
		{
			isEqual=this.right.exactlyEquals(p.right);//this��p����������Ϊ�գ��ݹ���ñȽ��������Ƿ����
		}
		else if(p.right==null&&this.right!=null||p.right!=null&&this.right==null)
		{
			isEqual=false;
		}
		return isEqual;
			
	}
	
	
	public BinaryNode<T> search (BinaryTree<T> pattern)//������thisΪ��������ǰ�����������һ��ƥ��pattern�������ĸ��ڵ�
	{
		BinaryNode<T> leftRet,rightRet;//��ֱ�ӷ��ر���������ֱ�ӷ���null�����߼�����
		if(this.exactlyEquals(pattern.root))//thisƥ�䷵��this
			return this;
		if(this.left!=null)
		{
			leftRet=this.left.search(pattern);//�ݹ���ã�this���������ǲ���pattern
		}
		else
			leftRet=null;
		if(this.right!=null)
		{
			rightRet=this.right.search(pattern);//�ݹ���ã�this���������ǲ���pattern
		}
		else
			rightRet=null;
		if(leftRet!=null)
			return leftRet;//������ƥ���򷵻����������ڵ�
		else if (rightRet!=null) 
		{
			return rightRet;//������ƥ���򷵻����������ڵ�
		}
		else 
		{
			return null;//����ƥ�䷵�ؿ�
		}
		
	}
	


}
