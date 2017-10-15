package sy2;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
public class func extends ActionSupport{
    private ArrayList<book> information;
    private ArrayList<author> author;
	private String authorname;
	private String Name;
	private mysqlconnection in;
	private book add_book;
	private book update_book;
	private author add_author;
	private book delete_book;
	private String deleteIsbn;
	private String old_isbn;
	private String error_inf;
	public String getError_inf() {
		return error_inf;
	}
	public void setError_inf(String error_inf) {
		this.error_inf = error_inf;
	}	
	public String getOld_isbn() {
		return old_isbn;
	}
	public void setOld_isbn(String old_isbn) {
		this.old_isbn = old_isbn;
	}
	public ArrayList<book> getInformation() {
		return information;
	}
	public void setInformation(ArrayList<book> information) {
		this.information = information;
	}
	    public String execute() {
	    return SUCCESS;
	    }
	    public String findp() {
	    	Name = Name + "dsb";
	    	return "success";
	    }
	    public book getUpdate_book() {
			return update_book;
		}
		public void setUpdate_book(book update_book) {
			this.update_book = update_book;
		}
		public ArrayList<author> getAuthor() {
			return author;
		}
		public void setAuthor(ArrayList<author> author) {
			this.author = author;
		}
		public String getAuthorname() {
			return authorname;
		}
		public void setAuthorname(String authorname) {
			this.authorname = authorname;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getDeleteIsbn() {
			return deleteIsbn;
		}
		public void setDeleteIsbn(String deleteIsbn) {
			this.deleteIsbn = deleteIsbn;
		}
		public book getAdd_book() {
			return add_book;
		}
		public void setAdd_book(book add_book) {
			this.add_book = add_book;
		}
		public author getAdd_author() {
			return add_author;
		}
		public void setAdd_author(author add_author) {
			this.add_author = add_author;
		}
		public book getDelete_book() {
			return delete_book;
		}
		public void setDelete_book(book delete_book) {
			this.delete_book = delete_book;
		}
		public String find() {
			   mysqlconnection in = new mysqlconnection();
			   ArrayList<book> inf = new ArrayList<book>();
			   ArrayList<author> inf2 = new ArrayList<author>();
			   int id= 0;
			   inf2 = in.getauthor();
			   if(authorname.isEmpty()||authorname.charAt(0) == ' ')
				   {
				   error_inf="你输入的作者信息为空或者首字母空格";
				   return "wrong input";
				   }
			   for(int i = 0;i<inf2.size();i++)
			   {
				   author e = inf2.get(i);
				   if(e.Name.equals(authorname))id=e.AuthorID;
			   }
			   
			   if (id==0) 
				   {
				   error_inf="你输入的作者信息不在数据库中，请先添加";
				    return "no author";
				    }
			   inf = in.getbook();
			   for(int i = 0;i<inf.size();i++)
			   {
				   book e = inf.get(i);
				   if(e.AuthorID != id)inf.remove(i--);  
			   }
			   information = inf;
			   return "success";
		    } 
		public String begin() {
	     mysqlconnection in = new mysqlconnection();
		 information = in.getbook();
		 author = in.getauthor();
		 return "success";
		}
		public String details() {
			System.out.println(Name);
			mysqlconnection in = new mysqlconnection();
			ArrayList<book> inf = new ArrayList<book>();
			ArrayList<author> inf2 = new ArrayList<author>();
			int id=0;
			inf = in.getbook();
			inf2 = in.getauthor();
			System.out.println("the long of inf is: " + inf.size());
			for(int i = 0;i<inf.size();i++)
			   {
				    book e = inf.get(i);		    
					if(!e.Title.equals(Name)) 
						{inf.remove(i--);
						}
					else id = e.AuthorID;
			   }
			
			for(int i = 0;i<inf2.size();i++)
			{
				 author e = inf2.get(i);
				 if(e.AuthorID != id ) inf2.remove(i--);
			}
			if(inf.size()==0)return "fail";			
			author = inf2;
			information = inf;
			return "success";
		}
		public String Ret() {
			mysqlconnection in = new mysqlconnection();
			System.out.println("the old_isbn is : "+old_isbn);
			author = in.getauthor();
			information = in.getbook();
			System.out.println("the old_isbn is : "+old_isbn);
			return "success";
		}
		public String addbook()
		{
				System.out.println("the book you add is"+add_book.Title);
				ArrayList<author> inf = new ArrayList<author>();
				String result;
				int mark = 0;
				int e1 ;
				e1=add_book.AuthorID;
				if(e1==0)
			    {
					error_inf = "你没有输入作者id无法存入数据库";
					return "fail";
					}
				mysqlconnection in = new mysqlconnection();
				inf = in.getauthor();
				result = in.addbook(add_book);
				for(int i = 0;i<inf.size();i++)
				{
					author e=inf.get(i);
					if(e.AuthorID == add_book.AuthorID)mark+=1;
				}
				if(mark == 0)return "can't find auther";
			return result;
		}
		public String addauthor()
		{
			mysqlconnection in = new mysqlconnection();
			System.out.println("the author you add is"+ add_author.Name);
			ArrayList<author> inf = new ArrayList<author>();
			inf=in.getauthor();
			for(int i=0;i<inf.size();i++)
			{
				author e = inf.get(i);
				if(e.AuthorID ==add_author.AuthorID)
					{
					    error_inf="想要添加的作者已存在";
					    return"fail";
			        }
		    }
			if(add_author.Name.isEmpty()||add_author.AuthorID==0) {
					error_inf = "作者名或ID未填写";
					return "fail";
					}
				String result;
				
				result = in.addauthor(add_author);
			return result;
		}
		public String db()
		{
			System.out.println("the Isbn you want to delete i : "+ deleteIsbn);
			mysqlconnection in = new mysqlconnection();
			String result;
			result = in.delbook(deleteIsbn);
			return result;
		}
		public String update()
		{
			String result;
			System.out.println("the old_isbn is : "+update_book.Isbn);
			if(update_book.AuthorID == 0 && update_book.price == 0 && update_book.PublishDate.equals("") && update_book.Publisher.equals(""))
			{
				
				error_inf="你没有更新任何信息";
				System.out.println(error_inf);
				return "fail";
			}
			mysqlconnection in = new mysqlconnection();
			result = in.updatebook(update_book, old_isbn);
			return result;
		}
		

}
