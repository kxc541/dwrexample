<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.delegator.SubNationalCodeServiceDelegator"%>
<%@page import="com.g4g.delegator.NationalCodeServiceDelegator"%>

<bean:define id="searchStr" value="" type="java.lang.String" />
<bean:define id="nextPages" value="0" type="java.lang.String" />
<bean:define id="prevPages" value="0" type="java.lang.String" />
<bean:define id="maxPage" value="0" type="java.lang.String" />
<bean:define id="totalPlayer" value="0" type="java.lang.String" />
<bean:define id="showAlls" value="false" type="java.lang.String" />
<bean:define id="allPlayer" value="" type="java.lang.String" />

<logic:present name="searchString">
	<bean:define id="searchStr" name="searchString" type="java.lang.String" />
</logic:present>

<logic:present name="showAllPeople">
	<bean:define id="showAlls" name="showAllPeople" type="java.lang.String" />
</logic:present>

<logic:present name="totalPlayers">
	<bean:define id="totalPlayer" name="totalPlayers"
		type="java.lang.String" />
</logic:present>

<logic:present name="nextPeoplePage">
	<bean:define id="nextPages" name="nextPeoplePage" type="java.lang.String" />
</logic:present>

<logic:present name="prevPeoplePage">
	<bean:define id="prevPages" name="prevPeoplePage" type="java.lang.String" />
</logic:present>
<logic:present name="maxPeoplePages">
	<bean:define id="maxPage" name="maxPeoplePages" type="java.lang.String" />
</logic:present>

<logic:present name="allPlayers">
	<bean:define id="allPlayer" name="allPlayers" type="java.lang.String" />
</logic:present>

<logic:present name="peopleSearchList">
	<bean:define id="peopleSearchList" name="peopleSearchList" />
	<logic:iterate id="searchedPeople" name="peopleSearchList"
		type="com.g4g.dto.SearchedPeople" length="<%=totalPlayer %>">
		<div class="Format1 clearfix">
			<div class="floatLeft width80 CenterAvatar">
				<jsp:include page="../common/avatar.jsp">
					<jsp:param name="playerid"
						value="<%=searchedPeople.getPlayerDTO().getId()%>" />
					<jsp:param name="avatarid"
						value="<%=(searchedPeople.getPlayerDTO()
												.getAvatars() != null) ? searchedPeople
										.getPlayerDTO().getAvatars().getId()
										: 0%>" />
					<jsp:param name="screenname"
						value="<%=searchedPeople.getPlayerDTO()
												.getScreenname()%>" />
					<jsp:param name="isOnline"
						value="<%=searchedPeople.getPlayerDTO().isIsonline()%>" />
					<jsp:param name="pictureId"
						value="<%=searchedPeople.getPlayerDTO().getPictureId()%>" />
					<jsp:param name="showMenu" value="false" />
					<jsp:param name="isLink" value="true" />
				</jsp:include>
			</div>
			<div class="floatLeft width140">
				<h2>
					<%=searchedPeople.getPlayerDTO()
												.getScreenname()%>
				</h2>
			</div>
			<div class="floatLeft width140">
				<table class="WidgetInfo Style1">
					<tbody>
						<tr></tr>
						<tr>
							<th>
								Location
							</th>
							<td>
								<logic:present name="searchedPeople" property="user">
									<bean:define id="user" name="searchedPeople" property="user"
										type="com.impessa.worldgaming.client.User" />
									<bean:write property="city" name="user" />

									<%
										if (user.getState() != null) {
														out.print(", ");
														out.print(SubNationalCodeServiceDelegator
																.getSubNationName(user.getState()));
													}

													if (user.getCountry() != null) {
														out.print(", ");
														out.print(NationalCodeServiceDelegator
																.getNationName(user.getCountry()));
													}
									%>
								</logic:present>

								<logic:notPresent name="searchedPeople" property="user">
									Location
								</logic:notPresent>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="floatRight grid3">
				<span class="buttons"><a class="blue"
					href="displayProfile.do?profileUserId=<%=searchedPeople.getPlayerDTO().getId()%>">Profile</a>
					<%
						if (!searchedPeople.isFriend()) {
					%> <a class="green"
					onClick="javascript:sendFriendRequestToUserWithTag('<%=DataUtil.encrypt(String
											.valueOf(searchedPeople
													.getPlayerDTO().getId()))%>')"
					href="javascript:{}">Add as Friend</a> <%
 	} else {
 %> <a class="green" style="cursor: pointer;" href="javascript:{}">Add
						as Friend</a> <%
 	}
 %> </span>
			</div>
		</div>
	</logic:iterate>
	<!-- format -->

	<logic:greaterThan value="5" name="allPlayer">
		<logic:equal value="false" name="showAlls">
			<div class="SearchOptions">
				<p class="floatRight">
					<a style="cursor: pointer;"
						onclick="javascript:retrivePeoplePage(<%=nextPages%>,<%=maxPage%>,'<%=searchStr%>','<%=allPlayer %>')">Next
					</a>
					<a style="cursor: pointer"
						onclick="javascript:retrivePeoplePage(<%=prevPages%>,<%=maxPage%>,'<%=searchStr%>','<%=allPlayer %>')">Previous
					</a>
				</p>

				<p>
					<a style="cursor: pointer"
						onclick="javascript:retriveAllPeople('<%=searchStr%>', <%=allPlayer %>)"> View
						all People</a>
				</p>
			</div>
		</logic:equal>
	</logic:greaterThan>
</logic:present>