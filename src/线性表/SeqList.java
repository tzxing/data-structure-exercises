package ���Ա�;

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
	public int insert(int i,T x)	//����ֵΪi��iΪ�����±�
	{
		if(x==null)
			throw new NullPointerException("x==null");
		if(i<0)
			i=0;
		if(i>this.n)
			i=this.n;
		Object[] source=this.element;//�������ø�ֵ
		if(this.n==element.length)//����������������˳������������
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
	
	public T remove(int i)          //ɾ����i��Ԫ�أ�0��i<n�����ر�ɾ��Ԫ�ء���iԽ�磬����null��//������iԽ�磬�׳����Խ���쳣
    {
        if (this.n>0 && i>=0 && i<this.n) 
        {
            T old = (T)this.element[i];                    //old�д洢��ɾ��Ԫ��
            for (int j=i; j<this.n-1; j++)
                this.element[j] = this.element[j+1];       //Ԫ��ǰ��һ��λ��
            this.element[this.n-1]=null;                   //��������Ԫ�ض���Ϊ�գ��ͷ�ԭ����ʵ��
            this.n--;
            return old;                                    //����old�ֲ��������õĶ��󣬴��ݶ�������
        }
        return null;
        //throw new IndexOutOfBoundsException(i+"");         //�׳����Խ���쳣
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
