<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="net.boffardi.decathlon.api.types.Discipline"%>

<jsp:include page="inc/header.jsp"/>

    <a class="navigation" href="list"><i class="inprogress fas fa-backspace"></i> Back to scoreboard</a>
             
    <div align="center">
        <c:if test="${perf != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${perf == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${perf != null}">
                        Edit athlete performance
                    </c:if>
                    <c:if test="${perf == null}">
                        Add new participant
                    </c:if>
                </h2>
            </caption>
                <c:if test="${perf != null}">
                    <input type="hidden" name="id" value="<c:out value='${perf.id}' />" />
                </c:if>           
            <tr>
                <th>First name: </th>
                <td>
                    <input type="text" name="firstName" 
                            value="<c:out value='${perf.firstName}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Last name: </th>
                <td>
                    <input type="text" name="lastName" 
                            value="<c:out value='${perf.lastName}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Discipline: </th>
                <td>
	                <c:set var="discipline" value="${perf.discipline}" scope="page" />
	                <%  Discipline discipline = (Discipline) pageContext.getAttribute("discipline"); %>
	                
                	<select name="discipline">
                		<option value="M" <% if (discipline == Discipline.MEN) { %>selected<%} %> >MEN</option>
                		<option value="W" <% if (discipline == Discipline.WOMEN) { %>selected<%} %> >WOMEN</option>
                	</select>
                </td>
            </tr>
            
            	
            <tr>
                <th>110/100m :</th>
                <td>
                    <input type="text" name="sprint"  class="seconds"
                            value="<c:out value='${perf.sprint}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Long Jump :</th>
                <td>
                    <input type="text" name="longJump"  class="centimeters"
                            value="<c:out value='${perf.longJump}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Shot Put :</th>
                <td>
                    <input type="text" name="shotPut"  class="meters"
                            value="<c:out value='${perf.shotPut}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>High Jump :</th>
                <td>
                    <input type="text" name="highJump"  class="centimeters"
                            value="<c:out value='${perf.highJump}' />"
                    />
                </td>
            </tr>
	
            <tr>
                <th>400m :</th>
                <td>
                    <input type="text" name="fourHundreds"  class="seconds"
                            value="<c:out value='${perf.fourHundreds}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <th>Hurdles :</th>
                <td>
                    <input type="text" name="hurdles"  class="seconds"
                            value="<c:out value='${perf.hurdles}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <th>Discus throw :</th>
                <td>
                    <input type="text" name="discus"  class="meters"
                            value="<c:out value='${perf.discus}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <th>Pole Vault :</th>
                <td>
                    <input type="text" name="poleVault"  class="meters"
                            value="<c:out value='${perf.poleVault}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <th>Javelin :</th>
                <td>
                    <input type="text" name="javelin"  class="meters"
                            value="<c:out value='${perf.javelin}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>1500m :</th>
                <td>
                    <input type="text" name="m1500sprint"  class="seconds"
                            value="<c:out value='${perf.m1500sprint}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   

<jsp:include page="inc/footer.jsp"/>
    