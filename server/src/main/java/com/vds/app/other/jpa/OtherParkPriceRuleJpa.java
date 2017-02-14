package com.vds.app.other.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vds.app.other.model.OtherParkPriceRule;

public interface OtherParkPriceRuleJpa extends JpaRepository<OtherParkPriceRule, String> {

	OtherParkPriceRule findByOtSiteIdAndOtCatalogId(String siteId, String catalogId);
}
