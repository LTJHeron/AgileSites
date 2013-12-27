<%@ taglib prefix="cs" uri="futuretense_cs/ftcs1_0.tld"
%><%@ page import="COM.FutureTense.Interfaces.*"
%><cs:ftcs><render:logdep cid='<%=ics.GetVar("eid")%>' c="CSElement"/><% 
  ics.SetVar("stream", wcs.core.WCS.dispatch(ics, "$site;format="normalize"$.element.Wrapper"));
  ics.CallElement("Streamer", new FTValList());
%></cs:ftcs>