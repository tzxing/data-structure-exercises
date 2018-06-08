package ����;

import ջ�Ͷ���.LinkedQueue;

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

	public TriNode<T> first(TriNode<T> p)//����pΪ���������У������и������µ�һ�����ʽ�㣬���Ǹ�������ߵ������㣬��Сֵ
	{
		if(p!=null)
			while(p.left!=null)
				p=p.left;
		return p;
	}
	public TriNode<T> next(TriNode<T> p)	//����p���и������µĺ�̽��
	{
		if(p!=null)
		{
			if(p.right!=null)	//��p���Һ��ӣ���p�ĺ��������������һ�����ʽ��
				return this.first(p.right);
			while(p.parent!=null)	//��pû���Һ��ӣ�����Ѱ��ĳ�����Ƚ��
			{
				if(p==p.parent.left)	//��p���丸ĸ�����ӣ���p�ĺ�����丸ĸ
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
    //����pΪ���������У������и����������һ�����ʽ�㣬���Ǹ������ұߵ������㣬���ֵ
    public TriNode<T> last(TriNode<T> p)
    {
        if (p != null)
            while (p.right != null)
                p = p.right;
        return p;
    }
    public TriNode<T> previous(TriNode<T> p)               //����p���и������µ�ǰ�����
    {
        if (p != null)
        {
            if (p.left!=null)                              //��p�����ӣ���p��ǰ���������������һ�����ʽ��
                return this.last(p.left);
            while (p.parent!=null)                         //��pû�����ӣ�����Ѱ��ĳ�����Ƚ��
            {
                if (p.parent.right==p)                     //��p���丸ĸ���Һ��ӣ���p��ǰ�����丸ĸ
                    return p.parent;
                p=p.parent;
            }
        }
        return null;
    }
    
    public boolean contains(T key)                         //�ж��Ƿ�����ؼ���ΪkeyԪ��
    {
        return this.searchNode(key)!=null;
    }
    public void addAll(T[] values)                         //����values��������Ԫ��
    {
        for (int i=0; i<values.length; i++)
            this.add(values[i]);                           //����Ԫ��
    }
    public void clear()                                    //ɾ������Ԫ��
    {
        this.root=null;                                    //Java�Զ��ͷŲ��ٱ�ʹ�õĴ洢��Ԫ
    }

    public int size()                                      //����Ԫ�ظ���
    {
    	//TODO
        return 0;//??this.set.size(); 
    }
    
    
  //ɾ���ؼ���Ϊkey��㣬���ر�ɾ��Ԫ�أ���û�ҵ���ɾ��������null���ǵݹ��㷨����key==null��Java�׳��ն����쳣
	public T remove(T key)
	{
		TriNode<T> p=this.searchNode(key);
		if(p!=null&&p.left!=null&&p.right!=null)//2�Ƚڵ�ת��Ϊɾ��1�Ƚڵ�
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
	
	public void printASL()                                 //���ƽ�����ҳ���ASL�����ҳɹ��������㹫ʽ���������Ĳ�α����㷨
    {
        System.out.print("ASL�ɹ�=(");
        LinkedQueue<TriNode<T>> que=new LinkedQueue<TriNode<T>>(); //�����ն���
        int asl=0, n=0, count=0, level=1;
        for (TriNode<T> p=this.root;  p!=null;  p=que.poll())   //����α����������������û�����
        {
            if (level(p)==level)                           //��p�����Ϊ��ǰ��Σ������
                 n++;                                      //��ǰ��Ľ�����
            else
            {
                System.out.print((asl==0 ? "" : "+")+level+"*"+n);//�����һ��ļ��㹫ʽ
                asl+=level*n;
                count+=n;                                  //�������Ľ�����
                level++;                                   //��һ��
                n=1;
            }
            if (p.left!=null)    
                que.add(p.left);                           //p�����ӽ�����
            if (p.right!=null)
                que.add(p.right);                          //p���Һ��ӽ�����
        }
        if (count==0)
            System.out.println(") = 0");
        else
        {
            System.out.print("+"+level+"*"+n);             //���һ��
            asl+=level*n;
            count+=n;
            System.out.println(")/"+count+" ="+asl+"/"+count+" ="+((asl+0.0)/count));
        }
    }   
	
	
	//����p������ڵĲ�Σ�������Ĳ��Ϊ1����������δ���ҵ�x����0
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
