package ���Ͷ�����;

import ջ�Ͷ���.LinkedStack;

public class BinaryTree<T>
{

	public BinaryNode<T> root;
	
	public BinaryTree()
	{
		this.root=null;
	}
	
	public boolean isEmpty()
	{
		return this.root==null;
	}
	
	public BinaryNode<T> insert(T x)//x����Ľڵ�Ϊ�µĸ��ڵ㣬ԭ��������Ϊ���Ӳ��룻���ز���ڵ�
	{
		return this.root=new BinaryNode<T>(x, this.root, null);
	}
	
	//��parent���������x����Ľڵ㣬parent��ԭ��/�Һ��ӳ�Ϊx����/�Һ��ӣ����ز���ڵ�
	public BinaryNode<T> insert(BinaryNode<T> parent,T x,boolean leftChild)
	{
		if(x==null)
			return null;
		if(leftChild)
			return parent.left=new BinaryNode<T>(x, parent.left, null);
		return parent.right=new BinaryNode<T>(x, null, parent.right);
	}
	
	public void remove(BinaryNode<T> parent,boolean leftChild)//ɾ��parent����/������
	{
		if(leftChild)
			parent.left=null;
		else
			parent.right=null;
	}
	public void clear()
	{
		this.root=null;
	}
	public void preorder()
	{
		preorder(this.root);
		System.out.println();
	}
	public void preorder(BinaryNode<T> p)
	{
		if(p!=null)
		{
			System.out.println(p.data.toString()+" ");
			preorder(p.left);
			preorder(p.right);
		}
	}
	
	
	//���ض������߶ȣ��ǵݹ��㷨
	//�������������ջ����󳤶ȼ�Ϊ�������ĸ߶�
	public int height()
	{
		int i=0;//��¼ջ�ĵ�ǰ����
		int max=0;//��¼ջ����󳤶�
		LinkedStack<BinaryNode<T>> stack=new LinkedStack<BinaryNode<T>>();
		BinaryNode<T> p=this.root;
		BinaryNode<T> pre=null;//��¼p����һ��λ�ã����������ѭ��
		stack.push(p);
		i++;
		while(!stack.isEmpty())
		{
			
			if(i>max)
				max=i;
			if(p.left!=null&&pre!=p.left&&pre!=p.right)//��p�����Ӳ�Ϊ����p���Ǵ�p�����Һ��ӷ���ʱ
			{
				pre=p;
				p=p.left;
				stack.push(p);
				i++;
			}
			else if(p.right!=null&&pre!=p.right)//��p���Һ��Ӳ�Ϊ����p���Ǵ��Һ��ӷ���ʱ
			{
				pre=p;
				p=p.right;
				stack.push(p);
				i++;
			}
			else//�����������غ��ջ
			{
				pre=stack.pop();
				i--;
				if(stack.peek()!=null)
					p=stack.peek();	
			}
//			System.out.println(stack.toString());
//			System.out.println(i);
		}

		return max;
	}
	
	public BinaryTree(T prelist[])//�Ա������������ȸ����й�����������ǵݹ��㷨
	{
		this();
		LinkedStack<BinaryNode<T>> stack=new LinkedStack<BinaryNode<T>>();
		int tag=0;//tagΪ1ʱ��ʾӦ������룬tag=2��ʾӦ���Ҳ��룬tag=3��ʾӦ��ջջ��Ԫ��
		BinaryNode<T> p=null;
		
		for(int i=0;i<prelist.length;i++)
		{
			if(i==0)
			{
				p=this.insert(prelist[i]);//������ڵ�
				stack.push(p);
//				System.out.println("push"+p);
				tag=1;	
				
				
			}
			else if(tag==1)
			{
				if(prelist[i]!=null)
				{
					p=this.insert(p, prelist[i], true);
					stack.push(p);
//					System.out.println("push"+p);
				}
				else
				{
					tag=2;
				}
				
				
			}
			else if(tag==2)
			{
				if(prelist[i]!=null)
				{
					p=this.insert(p, prelist[i], false);
					stack.push(p);
//					System.out.println("push"+p);
					tag=1;
				}
				else
				{
					tag=3;
				}
				
				
			}
			else if(tag==3)
			{
				BinaryNode<T> temp= stack.pop();
				p=stack.peek();
//				System.out.println("pop"+temp);
				if(stack.peek().right==temp)
				{
					i--;//������Һ��ӷ��أ��������ջ
				}
				
				else if(stack.peek()!=null)//���ӷ���tagΪ2�����Ҳ���
				{
					tag=2;
					i--;
				}
			}	
		}

		while(stack.peek()!=null)//���ջ
		{
			stack.pop();
//			System.out.println("pop"+stack.pop());
		}
	}	
	
	public BinaryTree<T> copy(BinaryTree<T> src)//��������ؿ���ʵ��
	{
		
		BinaryTree<T>p=new BinaryTree<>();
		p.root=src.root.copy(src.root);
		return p;
	}
	
	
	public void printGenList()//�ȸ������������������
    {
        System.out.print("�������Ĺ�����ʾ��");
        printGenList(this.root);
        System.out.println();
    }
    
    private void printGenList(BinaryNode<T> p)//��pΪ���ڵ�Ķ������Ĺ����������ݹ���ã��ȸ��������
    {
        if (p==null)                                       
            System.out.print("��");                         
        else            
        {   System.out.print(p.data.toString());          
            if (p.left!=null || p.right!=null)            
            {
                System.out.print("(");
                printGenList(p.left);                      
                System.out.print(",");
                printGenList(p.right);                    
                System.out.print(")");
            }
        }
    }
	
   public BinaryNode<T> search (BinaryTree<T> pattern)//�ȸ�������������׸���patternƥ������������ظ������ĸ��ڵ�
   {
	   return this.root.search(pattern);
   }

	public static void main(String[] args)
	{
		Integer test[]={1,2,3,null,4,null,null,null,5,6,null,null,7,8,null,null,9,10,null,null,11,null,null};
		BinaryTree<Integer> xBinaryTree=new BinaryTree<>(test);
		System.out.println(xBinaryTree.height());
	}
	
}

