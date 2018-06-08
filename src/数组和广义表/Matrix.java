package ����͹����;

public class Matrix
{

	protected int rows;                           //��������������
	public int columns;
    protected int[][] element;                             //��ά���飬�洢����Ԫ��

    public Matrix(int m, int n)                            //����m��n�������m��nΪ������Java�׳������鳤���쳣
    {
        this.element = new int[m][n];                      //����Ԫ�س�ֵΪ0
        this.rows = m;
        this.columns = n;
    }
    public Matrix(int n)                                   //����n��n�㷽��
    {
        this(n,n); 
    }
    public Matrix(int m, int n, int[][] value)             //����m��n������value[][]�ṩԪ��
    {
        this(m, n);
        for (int i=0; i<value.length && i<m; i++)          //valueԪ�ز���ʱ��0�����Զ���Ԫ��
            for (int j=0; j<value[i].length && j<n; j++)
               this.element[i][j] = value[i][j];
    }

    public int getRows()                                   //���ؾ�������
    {
        return this.rows;
    }
    public int getColumns()                                //���ؾ�������
    {
        return this.columns;
    }
    public int get(int i,int j)
    {
    	if(i>=0&&i<this.rows&&j>=0&&j<this.columns)
    		return this.element[i][j];
    	throw new IndexOutOfBoundsException("i="+i+",j="+j);
    }
    public void set(int i,int j,int x)
    {
    	if(i>=0&&i<this.rows&&j>=0&&j<this.columns)
    		this.element[i][j]=x;
    	else
    		throw new IndexOutOfBoundsException("i="+i+",j="+j);
    }
    public String toString()                               //���ؾ�������Ԫ�ص������ַ��������������
    {
        String str=" ����"+this.getClass().getName()+"��"+this.rows+"��"+this.columns+"����\n";
        for (int i=0; i<this.rows; i++)
        {
            for (int j=0; j<this.columns; j++)
                str+=String.format("%6d", this.element[i][j]); //"%6d"��ʽ��ʾʮ��������ռ6��
            str += "\n";
        }
        return str;
    }
    public void setRowsColumns(int m,int n)	//���þ���Ϊm��n�С�������ָ���������ϴ��򽫾������ݣ�������ԭ����Ԫ�ء�
    {
    	if(m>0&&n>0)
    	{
    		if (m>this.element.length || n>this.element[0].length)
    		{
    			int[][] source=this.element;
    			this.element = new int[m*2][n*2];              //���������ά����ռ䣬Ԫ�س�ֵΪ0
                for (int i=0; i<this.rows; i++)                //����ԭ��ά����Ԫ��
                    for(int j=0; j<this.columns; j++)
                        this.element[i][j] = source[i][j];
    		}
    		this.rows = m;
            this.columns = n;
    	}
    	else throw new IllegalArgumentException("�������������ܡ�0��m="+m+"��n="+n);
    }
    
   
    

}
