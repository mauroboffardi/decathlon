<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="net.boffardi.decathlon.api.types.Discipline"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="inc/header.jsp"/>

	<% Integer pos = 0; %>
	<% Boolean women = false; %>
	<% Discipline discipline; %>

    <div align="center">
        <table class="scoreboard" border="1" cellpadding="5">
            <caption>Decathlon Scoreboard</caption>
            <tr>
                <th>Discipline</th>
                <th>Position</th>
                <th>Last Name</th>
                <th>First Name</th>
                <th>100/110m Sprint</th>
                <th>Long Jump</th>
                <th>Shot Put</th>
                <th>High Jump</th>
                <th>400m Sprint</th>
                <th>Discus Throw</th>
                <th>Pole Vault</th>
                <th>Javelin Throw</th>
                <th>1500m Sprint</th>
                <th>Completed</th>
                <th>Score</th>
                <th><!-- edit/delete links --></th>
            </tr>
            <c:forEach var="perf" items="${scoreboard}">
	            <c:set var="discipline" value="${perf.discipline}" scope="page" />
				<%  // resets the position counter if we switch from male to female division.
					discipline = (Discipline) pageContext.getAttribute("discipline"); 
					if (women == false && discipline == Discipline.WOMEN) {
						pos = 0;
					}%>            
                <tr 
                	class="<% if (women) { 
                		%>women <% } 
                	else { %>men<%} %>"
                >
                    <td class="discipline"><c:out value="${perf.discipline}" /></td>
                    <td class="counter"><%= ++pos %></td>
                    <td class="name"><c:out value="${perf.lastName}" /></td>
                    <td class="name"><c:out value="${perf.firstName}" /></td>
                    <td class="result"><c:out value="${perf.sprint}" /></td>
                    <td class="result"><c:out value="${perf.longJump}" /></td>
                    <td class="result"><c:out value="${perf.shotPut}" /></td>
                    <td class="result"><c:out value="${perf.highJump}" /></td>
                    <td class="result"><c:out value="${perf.fourHundreds}" /></td>
                    <td class="result"><c:out value="${perf.discus}" /></td>
                    <td class="result"><c:out value="${perf.poleVault}" /></td>
                    <td class="result"><c:out value="${perf.javelin}" /></td>
                    <td class="result"><c:out value="${perf.m1500sprint}" /></td>
                    <td class="complete"><c:out value="${perf.complete}" />@todo format</td>
                    <td class="score"><c:out value="${perf.score}" /></td>

                    <td  class="buttons">
                        <a href="/edit?id=<c:out value='${perf.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${perf.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/new">Add New Participant</a>
    </div> 
<jsp:include page="inc/footer.jsp"/>