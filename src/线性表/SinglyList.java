package ÏßÐÔ±í;

public class SinglyList<T> extends Object
{
	public Node<T> head;

	public SinglyList()
	{
		this.head = new Node<T>();
	}
	public SinglyList(T[] values)
	{
		this();
		Node<T> rear=this.head;
		for(int i=0;i<values.length;i++)
		{
			rear.next=new Node<T>(values[i],null);
			rear=rear.next;
		}
	}
	public SinglyList(SinglyList<T> list)
	{
		this();
		Node<T> p=list.head;
		Node<T> q=this.head;
		while(p.next!=null)
		{
			q.next=new Node<T>(p.next.data,null);
			
			p=p.next;
			q=q.next;
		}

	}
	public boolean isEmpty()
	{
		return this.head.next==null;
	}
	public T get(int i)
	{
		Node<T> p=this.head.next;
		for(int j=0;j<i&&p!=null;j++)
			p=p.next;
		return (i>=0&&p!=null)?p.data:null;
	}
	public void set(int i,T x)
	{
		if(x==null)
			throw new NullPointerException();
		Node<T> p=this.head.next;
		for(int j=0;j<i&&p!=null;j++)
		{
			p=p.next;
		}
		p.data=x;
	}
	public int size()
	{
		Node<T> p=this.head.next;
		int j=0;
		while(p!=null)
		{
			p=p.next;
			j++;
		}
		return j;
	}
	public String toString()
	{
		String str=this.getClass().getName()+"(";
		for(Node<T> p=this.head.next;p!=null;p=p.next)
		{
			str+=p.data.toString();
			if(p.next!=null)
				str+=",";
		}
		return str+=")";
	}
	
	public Node<T> insert(int i,T x)
	{
		if(x==null)
			throw new NullPointerException();
		Node<T> front=this.head;
		for(int j=0;j<i&&front.next!=null;j++)
			front=front.next;
		front.next=new Node<T>(x,front.next);
		return front.next;
	}
	
	public Node<T> insert(T x)
	{
		return insert(Integer.MAX_VALUE,x);
	}
	
	public T remove(int i)
	{
		Node<T> front=this.head;
		for(int j=0;j<i&&front.next!=null;j++)
			front=front.next;
		if(i>=0&&front.next!=null)
		{
			T old=front.next.data;
			//System.out.println(front.next.next.data);
			front.next=front.next.next;
			return old;
		}
		return null;
	}
	public void clear()
	{
		this.head.next=null;	
	}
	public Node<T> search(T key)
	{
		Node<T> p=this.head;
		while(p!=null)
		{
			if(p.data==key)
				return p;
			else
				p=p.next;
		}
		return null;
	}
	public boolean contains(T key)
	{
		return this.search(key)!=null;
	}
	public Node<T> insertDifferent(T x)
	{
		Node<T> p=this.search(x);
		if(p!=null)
			return p;
		else
		{
			return insert(x);
		}
	}
	public T remove(T key)
	{
		if(key==null)
			throw new NullPointerException();
		Node<T> front=this.head;
		while(front.next!=null&&front.next.data!=key)
			front=front.next;
		if(front.next!=null)
		{
			T old=front.next.data;
			front.next=front.next.next;
			return old;
		}
		else
			return null;
			
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true;
		if (obj instanceof SinglyList<?>)
		{
			Node<T> p=this.head;
			SinglyList<T> list=(SinglyList<T>)obj;
			Node<T> q=list.head;
			if(this.size()!=list.size())
				return false;
			while(p.next!=null&&q.next!=null)
			{
				if(p.data!=q.data)
				{
					return false;
				}
				p=p.next;
				q=q.next;
			}
			return true;
		}
		return false;	
	}
	
	public SinglyList<T> subList(int begin, int end)
	{
		Node<T> p=this.head;
		SinglyList<T> retu=new SinglyList<T>();
		Node<T> q=retu.head;
		for(int i=0;p.next != null;p=p.next,i++)
		{
			if(i>=begin&&i<end)
			{
				q.next=new Node<T>(p.next.data,null);
				q=q.next;
			}
		}
		return retu;
		
	}
	
	
	
	public void removeAll(SinglyList<T> pattern)
	{
		Node<T> p=this.head;
		Node<T> x=this.head;
		Node<T> q=pattern.head;
		boolean tf=false;
		
		while(p.next!=null)
		{
			if(p.next.data==q.next.data)
			{
				x=p;
				while(q.next!=null&&x.next!=null)
				{
					x=x.next;
					q=q.next;
					
					if(x.data!=q.data||x.next==null&&q.next!=null)
					{
						tf=false;
						break;
					}
					tf=true;
				}
				if(tf)
				{
					p.next=x.next;
					q=pattern.head;
				}
				else
				{
					p=p.next;
					tf=false;
					q=pattern.head;
				}
			}
			else
				p=p.next;
		}	
	}
	

	

}
