package com.mall.mallmbg.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MallOrdersItemsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MallOrdersItemsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andItemsIdIsNull() {
            addCriterion("items_id is null");
            return (Criteria) this;
        }

        public Criteria andItemsIdIsNotNull() {
            addCriterion("items_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemsIdEqualTo(String value) {
            addCriterion("items_id =", value, "itemsId");
            return (Criteria) this;
        }

        public Criteria andItemsIdNotEqualTo(String value) {
            addCriterion("items_id <>", value, "itemsId");
            return (Criteria) this;
        }

        public Criteria andItemsIdGreaterThan(String value) {
            addCriterion("items_id >", value, "itemsId");
            return (Criteria) this;
        }

        public Criteria andItemsIdGreaterThanOrEqualTo(String value) {
            addCriterion("items_id >=", value, "itemsId");
            return (Criteria) this;
        }

        public Criteria andItemsIdLessThan(String value) {
            addCriterion("items_id <", value, "itemsId");
            return (Criteria) this;
        }

        public Criteria andItemsIdLessThanOrEqualTo(String value) {
            addCriterion("items_id <=", value, "itemsId");
            return (Criteria) this;
        }

        public Criteria andItemsIdLike(String value) {
            addCriterion("items_id like", value, "itemsId");
            return (Criteria) this;
        }

        public Criteria andItemsIdNotLike(String value) {
            addCriterion("items_id not like", value, "itemsId");
            return (Criteria) this;
        }

        public Criteria andItemsIdIn(List<String> values) {
            addCriterion("items_id in", values, "itemsId");
            return (Criteria) this;
        }

        public Criteria andItemsIdNotIn(List<String> values) {
            addCriterion("items_id not in", values, "itemsId");
            return (Criteria) this;
        }

        public Criteria andItemsIdBetween(String value1, String value2) {
            addCriterion("items_id between", value1, value2, "itemsId");
            return (Criteria) this;
        }

        public Criteria andItemsIdNotBetween(String value1, String value2) {
            addCriterion("items_id not between", value1, value2, "itemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdIsNull() {
            addCriterion("orders_id is null");
            return (Criteria) this;
        }

        public Criteria andOrdersIdIsNotNull() {
            addCriterion("orders_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrdersIdEqualTo(String value) {
            addCriterion("orders_id =", value, "ordersId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdNotEqualTo(String value) {
            addCriterion("orders_id <>", value, "ordersId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdGreaterThan(String value) {
            addCriterion("orders_id >", value, "ordersId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdGreaterThanOrEqualTo(String value) {
            addCriterion("orders_id >=", value, "ordersId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdLessThan(String value) {
            addCriterion("orders_id <", value, "ordersId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdLessThanOrEqualTo(String value) {
            addCriterion("orders_id <=", value, "ordersId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdLike(String value) {
            addCriterion("orders_id like", value, "ordersId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdNotLike(String value) {
            addCriterion("orders_id not like", value, "ordersId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdIn(List<String> values) {
            addCriterion("orders_id in", values, "ordersId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdNotIn(List<String> values) {
            addCriterion("orders_id not in", values, "ordersId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdBetween(String value1, String value2) {
            addCriterion("orders_id between", value1, value2, "ordersId");
            return (Criteria) this;
        }

        public Criteria andOrdersIdNotBetween(String value1, String value2) {
            addCriterion("orders_id not between", value1, value2, "ordersId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdIsNull() {
            addCriterion("product_sku_id is null");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdIsNotNull() {
            addCriterion("product_sku_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdEqualTo(Integer value) {
            addCriterion("product_sku_id =", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdNotEqualTo(Integer value) {
            addCriterion("product_sku_id <>", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdGreaterThan(Integer value) {
            addCriterion("product_sku_id >", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_sku_id >=", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdLessThan(Integer value) {
            addCriterion("product_sku_id <", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_sku_id <=", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdIn(List<Integer> values) {
            addCriterion("product_sku_id in", values, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdNotIn(List<Integer> values) {
            addCriterion("product_sku_id not in", values, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdBetween(Integer value1, Integer value2) {
            addCriterion("product_sku_id between", value1, value2, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_sku_id not between", value1, value2, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductAmountIsNull() {
            addCriterion("product_amount is null");
            return (Criteria) this;
        }

        public Criteria andProductAmountIsNotNull() {
            addCriterion("product_amount is not null");
            return (Criteria) this;
        }

        public Criteria andProductAmountEqualTo(Integer value) {
            addCriterion("product_amount =", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotEqualTo(Integer value) {
            addCriterion("product_amount <>", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountGreaterThan(Integer value) {
            addCriterion("product_amount >", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_amount >=", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountLessThan(Integer value) {
            addCriterion("product_amount <", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountLessThanOrEqualTo(Integer value) {
            addCriterion("product_amount <=", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountIn(List<Integer> values) {
            addCriterion("product_amount in", values, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotIn(List<Integer> values) {
            addCriterion("product_amount not in", values, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountBetween(Integer value1, Integer value2) {
            addCriterion("product_amount between", value1, value2, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("product_amount not between", value1, value2, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNull() {
            addCriterion("product_price is null");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNotNull() {
            addCriterion("product_price is not null");
            return (Criteria) this;
        }

        public Criteria andProductPriceEqualTo(BigDecimal value) {
            addCriterion("product_price =", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotEqualTo(BigDecimal value) {
            addCriterion("product_price <>", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThan(BigDecimal value) {
            addCriterion("product_price >", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_price >=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThan(BigDecimal value) {
            addCriterion("product_price <", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_price <=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceIn(List<BigDecimal> values) {
            addCriterion("product_price in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotIn(List<BigDecimal> values) {
            addCriterion("product_price not in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_price between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_price not between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPropertyIsNull() {
            addCriterion("product_property is null");
            return (Criteria) this;
        }

        public Criteria andProductPropertyIsNotNull() {
            addCriterion("product_property is not null");
            return (Criteria) this;
        }

        public Criteria andProductPropertyEqualTo(String value) {
            addCriterion("product_property =", value, "productProperty");
            return (Criteria) this;
        }

        public Criteria andProductPropertyNotEqualTo(String value) {
            addCriterion("product_property <>", value, "productProperty");
            return (Criteria) this;
        }

        public Criteria andProductPropertyGreaterThan(String value) {
            addCriterion("product_property >", value, "productProperty");
            return (Criteria) this;
        }

        public Criteria andProductPropertyGreaterThanOrEqualTo(String value) {
            addCriterion("product_property >=", value, "productProperty");
            return (Criteria) this;
        }

        public Criteria andProductPropertyLessThan(String value) {
            addCriterion("product_property <", value, "productProperty");
            return (Criteria) this;
        }

        public Criteria andProductPropertyLessThanOrEqualTo(String value) {
            addCriterion("product_property <=", value, "productProperty");
            return (Criteria) this;
        }

        public Criteria andProductPropertyLike(String value) {
            addCriterion("product_property like", value, "productProperty");
            return (Criteria) this;
        }

        public Criteria andProductPropertyNotLike(String value) {
            addCriterion("product_property not like", value, "productProperty");
            return (Criteria) this;
        }

        public Criteria andProductPropertyIn(List<String> values) {
            addCriterion("product_property in", values, "productProperty");
            return (Criteria) this;
        }

        public Criteria andProductPropertyNotIn(List<String> values) {
            addCriterion("product_property not in", values, "productProperty");
            return (Criteria) this;
        }

        public Criteria andProductPropertyBetween(String value1, String value2) {
            addCriterion("product_property between", value1, value2, "productProperty");
            return (Criteria) this;
        }

        public Criteria andProductPropertyNotBetween(String value1, String value2) {
            addCriterion("product_property not between", value1, value2, "productProperty");
            return (Criteria) this;
        }

        public Criteria andPromotionNameIsNull() {
            addCriterion("promotion_name is null");
            return (Criteria) this;
        }

        public Criteria andPromotionNameIsNotNull() {
            addCriterion("promotion_name is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionNameEqualTo(String value) {
            addCriterion("promotion_name =", value, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromotionNameNotEqualTo(String value) {
            addCriterion("promotion_name <>", value, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromotionNameGreaterThan(String value) {
            addCriterion("promotion_name >", value, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromotionNameGreaterThanOrEqualTo(String value) {
            addCriterion("promotion_name >=", value, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromotionNameLessThan(String value) {
            addCriterion("promotion_name <", value, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromotionNameLessThanOrEqualTo(String value) {
            addCriterion("promotion_name <=", value, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromotionNameLike(String value) {
            addCriterion("promotion_name like", value, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromotionNameNotLike(String value) {
            addCriterion("promotion_name not like", value, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromotionNameIn(List<String> values) {
            addCriterion("promotion_name in", values, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromotionNameNotIn(List<String> values) {
            addCriterion("promotion_name not in", values, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromotionNameBetween(String value1, String value2) {
            addCriterion("promotion_name between", value1, value2, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromotionNameNotBetween(String value1, String value2) {
            addCriterion("promotion_name not between", value1, value2, "promotionName");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceIsNull() {
            addCriterion("promption_price is null");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceIsNotNull() {
            addCriterion("promption_price is not null");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceEqualTo(BigDecimal value) {
            addCriterion("promption_price =", value, "promptionPrice");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceNotEqualTo(BigDecimal value) {
            addCriterion("promption_price <>", value, "promptionPrice");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceGreaterThan(BigDecimal value) {
            addCriterion("promption_price >", value, "promptionPrice");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("promption_price >=", value, "promptionPrice");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceLessThan(BigDecimal value) {
            addCriterion("promption_price <", value, "promptionPrice");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("promption_price <=", value, "promptionPrice");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceIn(List<BigDecimal> values) {
            addCriterion("promption_price in", values, "promptionPrice");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceNotIn(List<BigDecimal> values) {
            addCriterion("promption_price not in", values, "promptionPrice");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("promption_price between", value1, value2, "promptionPrice");
            return (Criteria) this;
        }

        public Criteria andPromptionPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("promption_price not between", value1, value2, "promptionPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceIsNull() {
            addCriterion("coupon_price is null");
            return (Criteria) this;
        }

        public Criteria andCouponPriceIsNotNull() {
            addCriterion("coupon_price is not null");
            return (Criteria) this;
        }

        public Criteria andCouponPriceEqualTo(BigDecimal value) {
            addCriterion("coupon_price =", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceNotEqualTo(BigDecimal value) {
            addCriterion("coupon_price <>", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceGreaterThan(BigDecimal value) {
            addCriterion("coupon_price >", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon_price >=", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceLessThan(BigDecimal value) {
            addCriterion("coupon_price <", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon_price <=", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceIn(List<BigDecimal> values) {
            addCriterion("coupon_price in", values, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceNotIn(List<BigDecimal> values) {
            addCriterion("coupon_price not in", values, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon_price between", value1, value2, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon_price not between", value1, value2, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andScorePriceIsNull() {
            addCriterion("score_price is null");
            return (Criteria) this;
        }

        public Criteria andScorePriceIsNotNull() {
            addCriterion("score_price is not null");
            return (Criteria) this;
        }

        public Criteria andScorePriceEqualTo(BigDecimal value) {
            addCriterion("score_price =", value, "scorePrice");
            return (Criteria) this;
        }

        public Criteria andScorePriceNotEqualTo(BigDecimal value) {
            addCriterion("score_price <>", value, "scorePrice");
            return (Criteria) this;
        }

        public Criteria andScorePriceGreaterThan(BigDecimal value) {
            addCriterion("score_price >", value, "scorePrice");
            return (Criteria) this;
        }

        public Criteria andScorePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("score_price >=", value, "scorePrice");
            return (Criteria) this;
        }

        public Criteria andScorePriceLessThan(BigDecimal value) {
            addCriterion("score_price <", value, "scorePrice");
            return (Criteria) this;
        }

        public Criteria andScorePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("score_price <=", value, "scorePrice");
            return (Criteria) this;
        }

        public Criteria andScorePriceIn(List<BigDecimal> values) {
            addCriterion("score_price in", values, "scorePrice");
            return (Criteria) this;
        }

        public Criteria andScorePriceNotIn(List<BigDecimal> values) {
            addCriterion("score_price not in", values, "scorePrice");
            return (Criteria) this;
        }

        public Criteria andScorePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score_price between", value1, value2, "scorePrice");
            return (Criteria) this;
        }

        public Criteria andScorePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score_price not between", value1, value2, "scorePrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceIsNull() {
            addCriterion("real_price is null");
            return (Criteria) this;
        }

        public Criteria andRealPriceIsNotNull() {
            addCriterion("real_price is not null");
            return (Criteria) this;
        }

        public Criteria andRealPriceEqualTo(BigDecimal value) {
            addCriterion("real_price =", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotEqualTo(BigDecimal value) {
            addCriterion("real_price <>", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceGreaterThan(BigDecimal value) {
            addCriterion("real_price >", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("real_price >=", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceLessThan(BigDecimal value) {
            addCriterion("real_price <", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("real_price <=", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceIn(List<BigDecimal> values) {
            addCriterion("real_price in", values, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotIn(List<BigDecimal> values) {
            addCriterion("real_price not in", values, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_price between", value1, value2, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_price not between", value1, value2, "realPrice");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andGrowthIsNull() {
            addCriterion("growth is null");
            return (Criteria) this;
        }

        public Criteria andGrowthIsNotNull() {
            addCriterion("growth is not null");
            return (Criteria) this;
        }

        public Criteria andGrowthEqualTo(Integer value) {
            addCriterion("growth =", value, "growth");
            return (Criteria) this;
        }

        public Criteria andGrowthNotEqualTo(Integer value) {
            addCriterion("growth <>", value, "growth");
            return (Criteria) this;
        }

        public Criteria andGrowthGreaterThan(Integer value) {
            addCriterion("growth >", value, "growth");
            return (Criteria) this;
        }

        public Criteria andGrowthGreaterThanOrEqualTo(Integer value) {
            addCriterion("growth >=", value, "growth");
            return (Criteria) this;
        }

        public Criteria andGrowthLessThan(Integer value) {
            addCriterion("growth <", value, "growth");
            return (Criteria) this;
        }

        public Criteria andGrowthLessThanOrEqualTo(Integer value) {
            addCriterion("growth <=", value, "growth");
            return (Criteria) this;
        }

        public Criteria andGrowthIn(List<Integer> values) {
            addCriterion("growth in", values, "growth");
            return (Criteria) this;
        }

        public Criteria andGrowthNotIn(List<Integer> values) {
            addCriterion("growth not in", values, "growth");
            return (Criteria) this;
        }

        public Criteria andGrowthBetween(Integer value1, Integer value2) {
            addCriterion("growth between", value1, value2, "growth");
            return (Criteria) this;
        }

        public Criteria andGrowthNotBetween(Integer value1, Integer value2) {
            addCriterion("growth not between", value1, value2, "growth");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIsNull() {
            addCriterion("comment_time is null");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIsNotNull() {
            addCriterion("comment_time is not null");
            return (Criteria) this;
        }

        public Criteria andCommentTimeEqualTo(Date value) {
            addCriterion("comment_time =", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotEqualTo(Date value) {
            addCriterion("comment_time <>", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeGreaterThan(Date value) {
            addCriterion("comment_time >", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("comment_time >=", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeLessThan(Date value) {
            addCriterion("comment_time <", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeLessThanOrEqualTo(Date value) {
            addCriterion("comment_time <=", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIn(List<Date> values) {
            addCriterion("comment_time in", values, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotIn(List<Date> values) {
            addCriterion("comment_time not in", values, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeBetween(Date value1, Date value2) {
            addCriterion("comment_time between", value1, value2, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotBetween(Date value1, Date value2) {
            addCriterion("comment_time not between", value1, value2, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentDetailIsNull() {
            addCriterion("comment_detail is null");
            return (Criteria) this;
        }

        public Criteria andCommentDetailIsNotNull() {
            addCriterion("comment_detail is not null");
            return (Criteria) this;
        }

        public Criteria andCommentDetailEqualTo(String value) {
            addCriterion("comment_detail =", value, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentDetailNotEqualTo(String value) {
            addCriterion("comment_detail <>", value, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentDetailGreaterThan(String value) {
            addCriterion("comment_detail >", value, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentDetailGreaterThanOrEqualTo(String value) {
            addCriterion("comment_detail >=", value, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentDetailLessThan(String value) {
            addCriterion("comment_detail <", value, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentDetailLessThanOrEqualTo(String value) {
            addCriterion("comment_detail <=", value, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentDetailLike(String value) {
            addCriterion("comment_detail like", value, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentDetailNotLike(String value) {
            addCriterion("comment_detail not like", value, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentDetailIn(List<String> values) {
            addCriterion("comment_detail in", values, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentDetailNotIn(List<String> values) {
            addCriterion("comment_detail not in", values, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentDetailBetween(String value1, String value2) {
            addCriterion("comment_detail between", value1, value2, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentDetailNotBetween(String value1, String value2) {
            addCriterion("comment_detail not between", value1, value2, "commentDetail");
            return (Criteria) this;
        }

        public Criteria andCommentPicIsNull() {
            addCriterion("comment_pic is null");
            return (Criteria) this;
        }

        public Criteria andCommentPicIsNotNull() {
            addCriterion("comment_pic is not null");
            return (Criteria) this;
        }

        public Criteria andCommentPicEqualTo(String value) {
            addCriterion("comment_pic =", value, "commentPic");
            return (Criteria) this;
        }

        public Criteria andCommentPicNotEqualTo(String value) {
            addCriterion("comment_pic <>", value, "commentPic");
            return (Criteria) this;
        }

        public Criteria andCommentPicGreaterThan(String value) {
            addCriterion("comment_pic >", value, "commentPic");
            return (Criteria) this;
        }

        public Criteria andCommentPicGreaterThanOrEqualTo(String value) {
            addCriterion("comment_pic >=", value, "commentPic");
            return (Criteria) this;
        }

        public Criteria andCommentPicLessThan(String value) {
            addCriterion("comment_pic <", value, "commentPic");
            return (Criteria) this;
        }

        public Criteria andCommentPicLessThanOrEqualTo(String value) {
            addCriterion("comment_pic <=", value, "commentPic");
            return (Criteria) this;
        }

        public Criteria andCommentPicLike(String value) {
            addCriterion("comment_pic like", value, "commentPic");
            return (Criteria) this;
        }

        public Criteria andCommentPicNotLike(String value) {
            addCriterion("comment_pic not like", value, "commentPic");
            return (Criteria) this;
        }

        public Criteria andCommentPicIn(List<String> values) {
            addCriterion("comment_pic in", values, "commentPic");
            return (Criteria) this;
        }

        public Criteria andCommentPicNotIn(List<String> values) {
            addCriterion("comment_pic not in", values, "commentPic");
            return (Criteria) this;
        }

        public Criteria andCommentPicBetween(String value1, String value2) {
            addCriterion("comment_pic between", value1, value2, "commentPic");
            return (Criteria) this;
        }

        public Criteria andCommentPicNotBetween(String value1, String value2) {
            addCriterion("comment_pic not between", value1, value2, "commentPic");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceIsNull() {
            addCriterion("promotion_price is null");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceIsNotNull() {
            addCriterion("promotion_price is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceEqualTo(BigDecimal value) {
            addCriterion("promotion_price =", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceNotEqualTo(BigDecimal value) {
            addCriterion("promotion_price <>", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceGreaterThan(BigDecimal value) {
            addCriterion("promotion_price >", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("promotion_price >=", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceLessThan(BigDecimal value) {
            addCriterion("promotion_price <", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("promotion_price <=", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceIn(List<BigDecimal> values) {
            addCriterion("promotion_price in", values, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceNotIn(List<BigDecimal> values) {
            addCriterion("promotion_price not in", values, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("promotion_price between", value1, value2, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("promotion_price not between", value1, value2, "promotionPrice");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}