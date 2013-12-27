<%@ taglib prefix="cs" uri="futuretense_cs/ftcs1_0.tld"
%><%@ taglib prefix="asset" uri="futuretense_cs/asset.tld"
%><%@ taglib prefix="assetset" uri="futuretense_cs/assetset.tld"
%><%@ taglib prefix="commercecontext" uri="futuretense_cs/commercecontext.tld"
%><%@ taglib prefix="ics" uri="futuretense_cs/ics.tld"
%><%@ taglib prefix="listobject" uri="futuretense_cs/listobject.tld"
%><%@ taglib prefix="render" uri="futuretense_cs/render.tld"
%><%@ taglib prefix="searchstate" uri="futuretense_cs/searchstate.tld"
%><%@ taglib prefix="siteplan" uri="futuretense_cs/siteplan.tld"
%><%@ page import="COM.FutureTense.Interfaces.*"
%><cs:ftcs><render:logdep cid='<%=ics.GetVar("tid")%>' c="Template"/><% 
  // Controller Logic 
  ics.SetVar("stream", wcs.core.WCS.dispatch(ics, "$class$"));
%><% // View Logic
  ics.CallElement("Streamer", new FTValList());
  // wcs.api.Env e = wcs.core.WCS.getEnv(ics, "$class$");
  // your JSP code here 
%></cs:ftcs>