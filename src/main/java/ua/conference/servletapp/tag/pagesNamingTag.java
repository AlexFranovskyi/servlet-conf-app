package ua.conference.servletapp.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 
 * @author Alexander
 * 
 * Creates custom tag to create list of int values, used to represent pages with paginating on JSP page.
 * Adds list of values as {@value var} attribute into JSP context using {@link SimpleTagSupport#getJspContext()}
 * Marks set of skipped page numbers as "-1"
 *
 */

public class pagesNamingTag extends SimpleTagSupport {
	private String var;
	private int totalPages;
	private int pageNumber;
	
	private List<Integer> list= new ArrayList<>();
	
	public void setvar(String var) {
		this.var = var;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public void setTotalPages(int pageAmount) {
		this.totalPages = pageAmount;
	}
	
	@Override
	public void doTag() throws JspException{
		if (totalPages > 7) {
			pageNumber += 1;
			
			ArrayList<Integer> head = new ArrayList<>();
			if (pageNumber > 4) {
				head.add(1);
				head.add(-1);
			} else {
				head.add(1);
				head.add(2);
				head.add(3);
			}
			
			ArrayList<Integer> tail = new ArrayList<>();
			if (pageNumber < totalPages - 3) {
				tail.add(-1);
				tail.add(totalPages);
			} else {
				tail.add(totalPages - 2);
				tail.add(totalPages - 1);
				tail.add(totalPages);
			}
			
			ArrayList<Integer> bodyBefore = new ArrayList<>();
			if (pageNumber > 4 && pageNumber < totalPages - 1) {
				bodyBefore.add(pageNumber - 2);
				bodyBefore.add(pageNumber - 1);
			}
			
			ArrayList<Integer> bodyAfter = new ArrayList<>();
			if (pageNumber > 2 && pageNumber < totalPages - 3) {
				bodyAfter.add(pageNumber + 1);
				bodyAfter.add(pageNumber + 2);
			}
			
			list.addAll(head);
			list.addAll(bodyBefore);
			
			if (pageNumber > 3 && pageNumber < totalPages - 2) {
				list.add(pageNumber);
			}
			
			list.addAll(bodyAfter);
			list.addAll(tail);
			
		} else {
			for (int i=1; i <= totalPages; i++) {
				list.add(i);
			}
		}
	
		getJspContext().setAttribute(var, list);
	}
	
}
