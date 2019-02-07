<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="net.boffardi.decathlon.api.types.Discipline"%>

<jsp:include page="inc/header.jsp"/>

    <a class="navigation" href="list"><i class="inprogress fas fa-backspace"></i> Back to scoreboard</a>
             
    <div>
        <c:if test="${perf != null}">
            <form action="update" method="post" class="form-style-4">
            <input type="hidden" name="id" value="<c:out value='${perf.id}' />" />
        </c:if>
        <c:if test="${perf == null}">
            <form action="insert" method="post" class="form-style-4">
        </c:if>
		<h2>
			<c:if test="${perf != null}">
	                        Edit athlete performance
	                    </c:if>
			<c:if test="${perf == null}">
	                        Add new participant
	                    </c:if>
		</h2>

        <label for="firstName">
				<span>First Name</span>
                <input type="text" name="firstName" 
                        value="<c:out value='${perf.firstName}' />"
                    />
        </label>
                    
        <label for="lastName">
				<span>Last Name</span>
                <input type="text" name="lastName" 
                        value="<c:out value='${perf.lastName}' />"
                />
        </label>
                
        <c:set var="discipline" value="${perf.discipline}" scope="page" />
        <%  Discipline discipline = (Discipline) pageContext.getAttribute("discipline"); %>
	    <label for="discipline">
				<span>Discipline</span>
                	<select name="discipline">
                		<option value="M" <% if (discipline == Discipline.MEN) { %>selected<%} %> >MEN</option>
                		<option value="W" <% if (discipline == Discipline.WOMEN) { %>selected<%} %> >WOMEN</option>
                	</select>
        </label>
            	
	    <label for="sprint">
				<span>110/100m sprint</span>
                    <input type="text" name="sprint"  class="seconds"
                            value="<c:out value='${perf.sprint}' />"
                    />
        </label>
        
        <label for="longJump">
                <span>Long Jump </span>
                    <input type="text" name="longJump"  class="centimeters"
                            value="<c:out value='${perf.longJump}' />"
                    />
        </label>
        
        <label for="shotPut">
                <span>Shot Put </span>
                    <input type="text" name="shotPut"  class="meters"
                            value="<c:out value='${perf.shotPut}' />"
                    />
            </label>
            
        <label for="highJump">
                <span>High Jump </span>
                    <input type="text" name="highJump"  class="centimeters"
                            value="<c:out value='${perf.highJump}' />"
                    />
            </label>
	
        <label for="fourHundreds">
                <span>400m sprint</span>
                    <input type="text" name="fourHundreds"  class="seconds"
                            value="<c:out value='${perf.fourHundreds}' />"
                    />
            </label>
            
        <label for="hurdles">
                <span>110/100m Hurdles </span>

                    <input type="text" name="hurdles"  class="seconds"
                            value="<c:out value='${perf.hurdles}' />"
                    />

        </label>
            
        <label for="discus">
                <span>Discus throw </span>

                    <input type="text" name="discus"  class="meters"
                            value="<c:out value='${perf.discus}' />"
                    />

            </label>
            
        <label for="poleVault">
                <span>Pole Vault </span>
                    <input type="text" name="poleVault"  class="meters"
                            value="<c:out value='${perf.poleVault}' />"
                    />
            </label>
            
        <label for="javelin">
                <span>Javelin </span>
                    <input type="text" name="javelin"  class="meters"
                            value="<c:out value='${perf.javelin}' />"
                    />
        </label>
        
        <label for="m1500sprint">
                <span>1500m sprint</span>
                    <input type="text" name="m1500sprint"  class="seconds"
                            value="<c:out value='${perf.m1500sprint}' />"
                    />
        </label>
            
        <label>
        	<span></span>
            <input type="submit" value="Save" />
        </label>

        </form>
    </div>   

<jsp:include page="inc/footer.jsp"/>
    