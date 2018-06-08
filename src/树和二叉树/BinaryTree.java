package 树和二叉树;

import 栈和队列.LinkedStack;

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
	
	public BinaryNode<T> insert(T x)//x构造的节点为新的根节点，原二叉树作为左孩子插入；返回插入节点
	{
		return this.root=new BinaryNode<T>(x, this.root, null);
	}
	
	//在parent的下面插入x构造的节点，parent的原左/右孩子成为x的左/右孩子；返回插入节点
	public BinaryNode<T> insert(BinaryNode<T> parent,T x,boolean leftChild)
	{
		if(x==null)
			return null;
		if(leftChild)
			return parent.left=new BinaryNode<T>(x, parent.left, null);
		return parent.right=new BinaryNode<T>(x, null, parent.right);
	}
	
	public void remove(BinaryNode<T> parent,boolean leftChild)//删除parent的左/右子树
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
	
	
	//返回二叉树高度，非递归算法
	//后序遍历二叉树栈的最大长度即为二叉树的高度
	public int height()
	{
		int i=0;//记录栈的当前长度
		int max=0;//记录栈的最大长度
		LinkedStack<BinaryNode<T>> stack=new LinkedStack<BinaryNode<T>>();
		BinaryNode<T> p=this.root;
		BinaryNode<T> pre=null;//记录p的上一个位置，避免进入死循环
		stack.push(p);
		i++;
		while(!stack.isEmpty())
		{
			
			if(i>max)
				max=i;
			if(p.left!=null&&pre!=p.left&&pre!=p.right)//当p的左孩子不为空且p不是从p的左右孩子返回时
			{
				pre=p;
				p=p.left;
				stack.push(p);
				i++;
			}
			else if(p.right!=null&&pre!=p.right)//当p的右孩子不为空且p不是从右孩子返回时
			{
				pre=p;
				p=p.right;
				stack.push(p);
				i++;
			}
			else//从右子树返回后出栈
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
	
	public BinaryTree(T prelist[])//以标明空子树的先根序列构造二叉树，非递归算法
	{
		this();
		LinkedStack<BinaryNode<T>> stack=new LinkedStack<BinaryNode<T>>();
		int tag=0;//tag为1时表示应该左插入，tag=2表示应该右插入，tag=3表示应出栈栈顶元素
		BinaryNode<T> p=null;
		
		for(int i=0;i<prelist.length;i++)
		{
			if(i==0)
			{
				p=this.insert(prelist[i]);//插入根节点
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
					i--;//如果从右孩子返回，则继续出栈
				}
				
				else if(stack.peek()!=null)//左孩子返回tag为2继续右插入
				{
					tag=2;
					i--;
				}
			}	
		}

		while(stack.peek()!=null)//清空栈
		{
			stack.pop();
//			System.out.println("pop"+stack.pop());
		}
	}	
	
	public BinaryTree<T> copy(BinaryTree<T> src)//深拷贝，返回拷贝实例
	{
		
		BinaryTree<T>p=new BinaryTree<>();
		p.root=src.root.copy(src.root);
		return p;
	}
	
	
	public void printGenList()//先根次序遍历，广义表输出
    {
        System.out.print("二叉树的广义表表示：");
        printGenList(this.root);
        System.out.println();
    }
    
    private void printGenList(BinaryNode<T> p)//以p为根节点的二叉树的广义表输出，递归调用，先根次序遍历
    {
        if (p==null)                                       
            System.out.print("∧");                         
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
	
   public BinaryNode<T> search (BinaryTree<T> pattern)//先根次序遍历查找首个与pattern匹配的子树，返回该子树的根节点
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

