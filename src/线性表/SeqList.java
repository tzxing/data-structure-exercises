package 线性表;

public class SeqList<T>
{

	protected Object[] element;
	protected int n;
	
	public SeqList(int length)
	{
		this.element=new Object[length];
		this.n=0;
	}
	public SeqList()
	{
		this(64);
	}
	public SeqList(T[] values)
	{
		this(values.length);
		for(int i=0;i<values.length;i++)
		{
			this.element[i]=values[i];
		}
		this.n=element.length;
	}
	
	public boolean isEmpty()
	{
		return this.n==0;
	}
	public int size()
	{
		return this.n;
	}
	public T get(int i)
	{
		if(i>=0&&i<this.n)
			return (T) this.element[i];
		return null;
	}
	public void set(int i,T x)
	{
		if(x==null)
			throw new NullPointerException("x=null");
		if(i>=0&&i<this.n)
			this.element[i]=x;
		else throw new java.lang.IndexOutOfBoundsException(i+"");
	}
	public String toString()
	{
		String str=this.getClass().getName()+"(";
		if(this.n>0)
			str+=this.element[0].toString();
		for(int i=1;i<n;i++)
			str+=","+this.element[i].toString();
		return str+")";
	}
	
	public void replaceFirst(T key,T x)
	{
		for(int i=0;i<this.n;i++)
		{
			if((T)this.element[i]==key)
			{
				this.element[i]=x;
				break;
			}		
		}
	}
	public int insert(int i,T x)	//返回值为i，i为数组下标
	{
		if(x==null)
			throw new NullPointerException("x==null");
		if(i<0)
			i=0;
		if(i>this.n)
			i=this.n;
		Object[] source=this.element;//数组引用赋值
		if(this.n==element.length)//若数组满，则扩充顺序表的数组容量
		{
			this.element=new Object[source.length*2];
			for(int j=0;j<i;j++)
				this.element[j]=source[j];
		}
		for(int j=this.n-1;j>=i;j--)
		{
			this.element[j+1]=source[j];
		}
		this.element[i]=x;
		this.n++;
		return i;
	}
	
	public int insert(T x)
	{
		return this.insert(this.n,x);
	}
	
	public T remove(int i)          //删除第i个元素，0≤i<n，返回被删除元素。若i越界，返回null。//？？若i越界，抛出序号越界异常
    {
        if (this.n>0 && i>=0 && i<this.n) 
        {
            T old = (T)this.element[i];                    //old中存储被删除元素
            for (int j=i; j<this.n-1; j++)
                this.element[j] = this.element[j+1];       //元素前移一个位置
            this.element[this.n-1]=null;                   //设置数组元素对象为空，释放原引用实例
            this.n--;
            return old;                                    //返回old局部变量引用的对象，传递对象引用
        }
        return null;
        //throw new IndexOutOfBoundsException(i+"");         //抛出序号越界异常
    }
	
	
	public static void main(String[] args)
	{
		Integer a[]={1,2,3,4,5,6,7,8,9,8};
		SeqList<Integer> x=new SeqList<>(a);
		System.out.println(x);
		x.replaceFirst(8, 888);
		System.out.println(x);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
