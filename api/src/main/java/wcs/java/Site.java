package wcs.java;

import java.util.List;

import com.fatwire.assetapi.common.SiteAccessException;
import com.fatwire.assetapi.data.AssetId;
import com.fatwire.assetapi.data.MutableAssetData;
import com.fatwire.assetapi.site.SiteInfo;
import com.fatwire.assetapi.site.SiteManager;

/**
 * Represent a site - currently only the name of the site is stored
 * 
 * It will contains much more in future - some private code is already availabe
 * 
 * @author msciab
 * 
 */
public class Site {

	private String name;

	private com.fatwire.assetapi.site.Site site;

	private static Log log = new Log(Setup.class);

	public Site(String name) {
		this.name = name;
	}

	// future use
	@SuppressWarnings("unused")
	private Site(final Long id, final String name, final String description,
			final String[] types, final String[] users, final String[] roles) {
		super();

		this.name = name;

		site = new com.fatwire.assetapi.site.Site() {

			@Override
			public List<String> getAssetTypes() {
				return Util.array2listString(types);
			}

			@Override
			public String getCSPrefix() {
				return null;
			}

			@Override
			public String getCSPreview() {
				return null;
			}

			@Override
			public AssetId getCSPreviewAsset() {
				return null;
			}

			@Override
			public String getDescription() {
				return description;
			}

			@Override
			public Long getId() {
				return id;
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public String getPubroot() {
				return null;
			}

			@Override
			public List<String> getUserRoles(String arg0) {
				return Util.array2listString(roles);
			}

			@Override
			public List<String> getUsers() {
				return Util.array2listString(users);

			}

			@Override
			public void setAssetTypes(List<String> arg0) {

			}

			@Override
			public void setCSPrefix(String arg0) {

			}

			@Override
			public void setCSPreview(String arg0) {

			}

			@Override
			public void setCSPreviewAsset(AssetId arg0) {

			}

			@Override
			public void setDescription(String arg0) {

			}

			@Override
			public void setId(Long arg0) {

			}

			@Override
			public void setName(String arg0) {

			}

			@Override
			public void setPubroot(String arg0) {

			}

			@Override
			public void setUserRoles(String arg0, List<String> arg1) {

			}
		};
	}

	// update the site enabling asset types
	@SuppressWarnings("unchecked")
	String createOrUpdate(SiteManager sim) throws SiteAccessException {
		// sim.update(arg0
		boolean found = false;
		for (SiteInfo inf : sim.list()) {
			// {dbg(inf.getName());
			if (inf.getName().equals(site.getName()))
				found = true;
		}
		if (!found) {
			log.info("Creating  " + site);
			sim.create(Util.list(site));
		} else {
			log.info("Updating site " + site);
			sim.update(Util.list(site));
		}
		return site.getName();
	}

	/**
	 * Set site data
	 * 
	 * @param data
	 */
	// FIXME full implementation - for now just fix the current site
	// @SuppressWarnings({ "rawtypes", "unchecked" })
	public void setData(MutableAssetData data) {

		data.getAttributeData("Publist").setDataAsList(Util.listString(name));
		/*
		 * AttributeData attrs = data.getAttributeData("Publist");
		 * 
		 * List list = attrs.getDataAsList(); if (list == null) list = new
		 * LinkedList(); for (Object obj : list) if
		 * (obj.toString().equals(name)) {
		 * log.trace("site already in place, nothing to do"); return; }
		 * list.add(name); log.trace("Publist=" + list);
		 * 
		 * attrs.setDataAsList(Util.listString(name));
		 */
	}

	public String toString() {
		return "Site(" + site.getName() + ":" + site.getId() + ")";
	}
}