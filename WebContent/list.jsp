<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="net.boffardi.decathlon.api.types.Discipline"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="inc/header.jsp"/>

	<% Integer pos = 0; %>
	<% Boolean women = false; %>
	<% Boolean complete; %>
	<% Discipline discipline; %>

    <div align="center">
        <table class="scoreboard" border="1" cellpadding="5">
            <c:forEach var="perf" items="${scoreboard}">
	            <c:set var="discipline" value="${perf.discipline}" scope="page" />
				<%  // resets the position counter if we switch from male to female division.
					discipline = (Discipline) pageContext.getAttribute("discipline"); 
					if (women == false && discipline == Discipline.WOMEN) {
						women = true;
						pos = 0;
					} %>      
				
				<% if (pos == 0) { %>
					<tr>
						<td class="divisionheader" colspan="14">
						<% if (women) {%>WOMEN<% } else { %>MEN<% } %>
						</td>
					<tr>
		            <tr>
		                <th>Position</th>
		                <th>Name</th>
		                <th><% if (women) {%>100m<% } else { %>110m<% } %> Sprint</th>
		                <th>Long Jump</th>
		                <th>Shot Put</th>
		                <th>High Jump</th>
		                <th>400m Sprint</th>
		                <th><% if (women) {%>100m<% } else { %>110m<% } %> Hurdles</th>
		                <th>Discus Throw</th>
		                <th>Pole Vault</th>
		                <th>Javelin Throw</th>
		                <th>1500m Sprint</th>
		                <th>Score</th>
		                <th><!-- edit/delete links --></th>
		            </tr>
		        <% } %>
					
				<% ++pos; %>	      
                <tr 
                	class="<% if (women) { 
                		%>women <% } 
                	else { 
                		%>men<% 
                	} %> 
                	position<%=pos%>
                	">
                
                    <td class="position"><%= pos %></td>
                    <td class="name"><c:out value="${perf.lastName}" />, <c:out value="${perf.firstName}" /></td>
                    <td class="result"><c:out value="${perf.sprint}" /></td>
                    <td class="result"><c:out value="${perf.longJump}" /></td>
                    <td class="result"><c:out value="${perf.shotPut}" /></td>
                    <td class="result"><c:out value="${perf.highJump}" /></td>
                    <td class="result"><c:out value="${perf.fourHundreds}" /></td>
                    <td class="result"><c:out value="${perf.hurdles}" /></td>
                    <td class="result"><c:out value="${perf.discus}" /></td>
                    <td class="result"><c:out value="${perf.poleVault}" /></td>
                    <td class="result"><c:out value="${perf.javelin}" /></td>
                    <td class="result"><c:out value="${perf.m1500sprint}" /></td>
                    
                    <c:set var="complete" value="${perf.complete}" scope="page" />
                    <td class="score">
                    	<%  complete = (Boolean) pageContext.getAttribute("complete");  
                    		if (!complete) {
                    			%><a href="#" title="Race still in progress" ><i class="inprogress fas fa-running"></i></a>&nbsp;<% 
                    		}
                    	%>
                    	<c:out value="${perf.score}" />
                    </td>

                    <td  class="buttons">
                        <a href="edit?id=<c:out value='${perf.id}'/>" title="Edit"><i class="inprogress fas fa-edit"></i></a>
                        &nbsp;
                        <a href="delete?id=<c:out value='${perf.id}'/>" title="Delete" 
                        	onclick="return confirm('Are you sure you want to delete this participant?');"><i class="inprogress fas fa-user-times"></i></a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <a class="navigation" href="new"><i class="inprogress fas fa-user-plus"></i> Add new participant</a>
    </div> 
<jsp:include page="inc/footer.jsp"/>