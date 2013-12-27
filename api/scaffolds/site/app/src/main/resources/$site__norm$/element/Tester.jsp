<%@ taglib prefix="cs" uri="futuretense_cs/ftcs1_0.tld"
%><%@ page import="COM.FutureTense.Interfaces.*"
%><cs:ftcs><% 
  ics.SetVar("stream", wcs.core.WCS.dispatch(ics, "$site;format="normalize"$.element.Tester"));
  ics.CallElement("Streamer", new FTValList());
%></cs:ftcs>