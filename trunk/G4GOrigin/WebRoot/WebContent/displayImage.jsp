<%@page import="com.g4g.dto.SearchCriteria"%>
<%@page import="java.util.List"%>
<%@page import="com.g4g.delegator.PicturesServiceDelegator"%>
<%@page import="com.g4g.dto.PicturesDTO"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.AvatarsDTO"%>
<html>
	<body>
		<%
		byte[] photoData = null;
				String strDTOType = request.getParameter(G4GConstants.DTO);
				String id = request.getParameter(G4GConstants.ID);
				SearchCriteria picSearchCriteria = new SearchCriteria();
				picSearchCriteria.setAttribute(G4GConstants.ID, Integer.parseInt(id));
				if (strDTOType.equals(G4GConstants.PICTUREDTO)) {
					List<PicturesDTO> picList = PicturesServiceDelegator.getList(picSearchCriteria);
					if (picList.size() > 0) {
						PicturesDTO picturesDTO = (PicturesDTO) picList.get(0);
						photoData = picturesDTO.getImage();
					}
				} else if (strDTOType.equals(G4GConstants.AVATARDTO)) {
						AvatarsDTO avatarsDTO = (AvatarsDTO)application.getAttribute("avatar"+id);
						photoData = avatarsDTO.getImage();
				}
			
			//int imageId = Integer.parseInt(decStrgImageId);
			response.setContentType("image/jpg");
			response.setContentLength(photoData.length);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(photoData);
			outputStream.flush();
			outputStream.close();
			out.clear();
			out = pageContext.pushBody();
		%>
	</body>
</html>
