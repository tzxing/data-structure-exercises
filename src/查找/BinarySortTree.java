package 查找;

import 栈和队列.LinkedQueue;

public class BinarySortTree<T extends Comparable<? super T>> 
{
	public TriNode<T> root;
	
	public BinarySortTree()
	{
		this.root=null;
	}
	public BinarySortTree(T[] values)
	{
		this();
		for(int i=0;i<values.length;i++)
		{
			this.add(values[i]);
		}
	}
	
	public boolean isEmpty()
	{
		return this.root==null;
	}
	public TriNode<T> searchNode(T key)
	{
		TriNode<T> p=this.root;
		while(p!=null&&key.compareTo(p.data)!=0)
		{
			if(key.compareTo(p.data)<0)
				p=p.left;
			else
				p=p.right;
		}
		return p!=null?p:null;
	}
	public T search(T key)
	{
		TriNode<T> find=this.searchNode(key);
		return find!=null?find.data:null;
	}
	public boolean add(T x)
	{
		if(this.root==null)
			this.root=new TriNode<>(x);
		else
		{
			TriNode<T> p=this.root,parent=null;
			while(p!=null)
			{
				if(x.compareTo(p.data)==0)
					return false;
				parent=p;
				if(x.compareTo(p.data)<0)
					p=p.left;
				else
					p=p.right;
			}
			if(x.compareTo(parent.data)<0)
				parent.left=new TriNode<T>(x,parent,null,null);
			else
				parent.right=new TriNode<T>(x, parent, null, null);
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		String str="[";
		TriNode<T> p=this.first(this.root);
		while(p!=null)
		{
			str+=p.data.toString()+" ";
			p=this.next(p);
		}
		return str+"]";
				
				
	}

	public TriNode<T> first(TriNode<T> p)//在以p为根的子树中，返回中根次序下第一个访问结点，即是根的最左边的子孙结点，最小值
	{
		if(p!=null)
			while(p.left!=null)
				p=p.left;
		return p;
	}
	public TriNode<T> next(TriNode<T> p)	//返回p在中根次序下的后继结点
	{
		if(p!=null)
		{
			if(p.right!=null)	//若p有右孩子，则p的后继是其右子树第一个访问结点
				return this.first(p.right);
			while(p.parent!=null)	//若p没有右孩子，向上寻找某个祖先结点
			{
				if(p==p.parent.left)	//若p是其父母的左孩子，则p的后继是其父母
					return p.parent;
				p=p.parent;
			}
		}
		return null;
	}
	
	public void inorderPrevious()
    {
        System.out.print("["); 
        for (TriNode<T> p=this.last(this.root);  p!=null;  p=this.previous(p)) 
            System.out.print(p.data.toString()+" ");
        System.out.println("]");
    }
    //在以p为根的子树中，返回中根次序下最后一个访问结点，即是根的最右边的子孙结点，最大值
    public TriNode<T> last(TriNode<T> p)
    {
        if (p != null)
            while (p.right != null)
                p = p.right;
        return p;
    }
    public TriNode<T> previous(TriNode<T> p)               //返回p在中根次序下的前驱结点
    {
        if (p != null)
        {
            if (p.left!=null)                              //若p有左孩子，则p的前驱是其左子树最后一个访问结点
                return this.last(p.left);
            while (p.parent!=null)                         //若p没有左孩子，向上寻找某个祖先结点
            {
                if (p.parent.right==p)                     //若p是其父母的右孩子，则p的前驱是其父母
                    return p.parent;
                p=p.parent;
            }
        }
        return null;
    }
    
    public boolean contains(T key)                         //判断是否包含关键字为key元素
    {
        return this.searchNode(key)!=null;
    }
    public void addAll(T[] values)                         //插入values数组所有元素
    {
        for (int i=0; i<values.length; i++)
            this.add(values[i]);                           //插入元素
    }
    public void clear()                                    //删除所有元素
    {
        this.root=null;                                    //Java自动释放不再被使用的存储单元
    }

    public int size()                                      //返回元素个数
    {
    	//TODO
        return 0;//??this.set.size(); 
    }
    
    
  //删除关键字为key结点，返回被删除元素；若没找到则不删除，返回null。非递归算法，若key==null，Java抛出空对象异常
	public T remove(T key)
	{
		TriNode<T> p=this.searchNode(key);
		if(p!=null&&p.left!=null&&p.right!=null)//2度节点转化为删除1度节点
		{
			TriNode<T> insucc=this.first(p.right);
			T temp=p.data;
			p.data=insucc.data;
			insucc.data=temp;
			p=insucc;
		}
		if(p!=null&&p==this.root)
		{
			if(this.root.left!=null)
				this.root=p.left;
			else
				this.root=p.right;
			if(this.root!=null)
				this.root.parent=null;
			return p.data;
		}
		if(p!=null&&p==p.parent.left)
		{
			if(p.left!=null)
			{
				p.parent.left=p.left;
				p.left.parent=p.parent;
			}
			else
			{
				p.parent.left=p.right;
				if(p.right!=null)
					p.right.parent=p.parent;
			}
		}
		if(p!=null&&p==p.parent.right)
		{
			if(p.left!=null)
			{
				p.parent.right=p.left;
				p.left.parent=p.parent;
			}
			else
			{
				p.parent.right=p.right;
				if(p.right!=null)
					p.right.parent=p.parent;
			}
		}
		return p!=null?p.data:null;
	}
	
	public void printASL()                                 //输出平均查找长度ASL（查找成功）及计算公式，二叉树的层次遍历算法
    {
        System.out.print("ASL成功=(");
        LinkedQueue<TriNode<T>> que=new LinkedQueue<TriNode<T>>(); //创建空队列
        int asl=0, n=0, count=0, level=1;
        for (TriNode<T> p=this.root;  p!=null;  p=que.poll())   //按层次遍历二叉树，根结点没有入队
        {
            if (level(p)==level)                           //若p结点层次为当前层次，则计数
                 n++;                                      //当前层的结点个数
            else
            {
                System.out.print((asl==0 ? "" : "+")+level+"*"+n);//输出上一层的计算公式
                asl+=level*n;
                count+=n;                                  //二叉树的结点个数
                level++;                                   //深一层
                n=1;
            }
            if (p.left!=null)    
                que.add(p.left);                           //p的左孩子结点入队
            if (p.right!=null)
                que.add(p.right);                          //p的右孩子结点入队
        }
        if (count==0)
            System.out.println(") = 0");
        else
        {
            System.out.print("+"+level+"*"+n);             //最后一层
            asl+=level*n;
            count+=n;
            System.out.println(")/"+count+" ="+asl+"/"+count+" ="+((asl+0.0)/count));
        }
    }   
	
	
	//返回p结点所在的层次，令根结点的层次为1，若空树或未查找到x返回0
    private int level(TriNode<T> p)
    { 
        int level = 0;
        while (p!=null)
        {   level++;
            p=p.parent;
        }
        return level;
    }
	
	public static void main(String[] args)
	{
		Integer[] integers={10,1,2,11,3,4,5,6,7,8,9};
		BinarySortTree<Integer> binarySortTree=new BinarySortTree<>(integers);
		System.out.println(binarySortTree.toString());
		binarySortTree.printASL();
	}
	
	
	
	
}
