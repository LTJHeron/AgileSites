package wcs.java;

import wcs.core.Env;
import wcs.core.Id;
import wcs.core.Log;
import wcs.core.Common;
import wcs.core.tag.AssetTag;
import wcs.core.tag.SiteplanTag;
import COM.FutureTense.Interfaces.ICS;

public class SitePlan implements wcs.core.SitePlan {

	final static Log log = Log.getLog(SitePlan.class);

	private Env e;
	private ICS i;

	private Id currentId;
	private String currentNid;

	/**
	 * Create a siteplan object that points to the root (the publication node)
	 * 
	 * @param e
	 */
	public SitePlan(Env e) {
		this.e = e;
		this.i = e.ics();
		goTo(e.getSitePlanRoot(e.getSiteName()));
	}

	/* (non-Javadoc)
	 * @see wcs.java.ISitePlan#current()
	 */
	@Override
	public Id current() {
		return currentId;
	}

	/* (non-Javadoc)
	 * @see wcs.java.ISitePlan#goTo(wcs.core.Id)
	 */
	@Override
	public SitePlan goTo(Id id) {

		String list = Common.tmp();
		String name = Common.tmp();

		if (id.c.equals("Publication")) {
			SiteplanTag.root().objectid(id.cid.toString()).list(list).run(i);
			currentId = new Id(e.getString(list, "otype"), e.getLong(list,
					"oid"));
			currentNid = e.getString(list, "nid");
        } else if (id.c.equals("SitePlan")) {
            AssetTag.load().name(name).type(id.c).objectid(id.cid.toString()).run(i);
            currentNid = AssetTag.getsitenode().name(name).eval(i, "output");
            currentId =  id;
		} else {
			AssetTag.load().name(name).type(id.c).objectid(id.cid.toString())
					.run(i);
			currentNid = AssetTag.getsitenode().name(name).eval(i, "output");
		}
		log.trace("SitePlan.goTo: id=%s nid=%s", currentId.toString(),
				currentNid);
		return this;
	}

	/* (non-Javadoc)
	 * @see wcs.java.ISitePlan#children()
	 */
	@Override
	public Id[] children() {
		log.trace("children!");
		String name = Common.tmp();
		String list = Common.tmp();
		SiteplanTag.load().name(name).nodeid(currentNid).run(i);
		SiteplanTag.children()//
				.name(name).code("Placed").order("nrank") //
				.list(list).run(i);
		return ilist2aid(list);
	}

	/* (non-Javadoc)
	 * @see wcs.java.ISitePlan#path()
	 */
	@Override
	public Id[] path() {
		log.trace("path");
		String name = Common.tmp();
		String list = Common.tmp();
		SiteplanTag.load().name(name).nodeid(currentNid).run(i);
		SiteplanTag.nodepath()//
				.name(name) //
				.list(list).run(i);
		return ilist2aid(list);
	}

	private Id[] ilist2aid(String list) {
		try {
			Id[] id = new Id[e.getSize(list)];
			log.trace("children #%d", id.length);
			for (int i : e.getRange(list)) {
				String c = e.getString(list, i, "otype");
				Long cid = e.getLong(list, i, "oid");
				id[i - 1] = new Id(c, cid);
				log.trace("%s", id[i - 1].toString());
			}
			return id;
		} catch (Exception ex) {
			log.trace(ex, "ops");
			return new Id[0];
		}
	}
}
