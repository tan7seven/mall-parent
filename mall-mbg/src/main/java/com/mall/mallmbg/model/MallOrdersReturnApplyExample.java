package com.mall.mallmbg.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MallOrdersReturnApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MallOrdersReturnApplyExample() {
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

        public Criteria andApplyIdIsNull() {
            addCriterion("apply_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNotNull() {
            addCriterion("apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyIdEqualTo(String value) {
            addCriterion("apply_id =", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotEqualTo(String value) {
            addCriterion("apply_id <>", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThan(String value) {
            addCriterion("apply_id >", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThanOrEqualTo(String value) {
            addCriterion("apply_id >=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThan(String value) {
            addCriterion("apply_id <", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThanOrEqualTo(String value) {
            addCriterion("apply_id <=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLike(String value) {
            addCriterion("apply_id like", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotLike(String value) {
            addCriterion("apply_id not like", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIn(List<String> values) {
            addCriterion("apply_id in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotIn(List<String> values) {
            addCriterion("apply_id not in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdBetween(String value1, String value2) {
            addCriterion("apply_id between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotBetween(String value1, String value2) {
            addCriterion("apply_id not between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyCodeIsNull() {
            addCriterion("apply_code is null");
            return (Criteria) this;
        }

        public Criteria andApplyCodeIsNotNull() {
            addCriterion("apply_code is not null");
            return (Criteria) this;
        }

        public Criteria andApplyCodeEqualTo(String value) {
            addCriterion("apply_code =", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotEqualTo(String value) {
            addCriterion("apply_code <>", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeGreaterThan(String value) {
            addCriterion("apply_code >", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("apply_code >=", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeLessThan(String value) {
            addCriterion("apply_code <", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeLessThanOrEqualTo(String value) {
            addCriterion("apply_code <=", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeLike(String value) {
            addCriterion("apply_code like", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotLike(String value) {
            addCriterion("apply_code not like", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeIn(List<String> values) {
            addCriterion("apply_code in", values, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotIn(List<String> values) {
            addCriterion("apply_code not in", values, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeBetween(String value1, String value2) {
            addCriterion("apply_code between", value1, value2, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotBetween(String value1, String value2) {
            addCriterion("apply_code not between", value1, value2, "applyCode");
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

        public Criteria andOrdersItemsIdIsNull() {
            addCriterion("orders_items_id is null");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdIsNotNull() {
            addCriterion("orders_items_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdEqualTo(String value) {
            addCriterion("orders_items_id =", value, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdNotEqualTo(String value) {
            addCriterion("orders_items_id <>", value, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdGreaterThan(String value) {
            addCriterion("orders_items_id >", value, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdGreaterThanOrEqualTo(String value) {
            addCriterion("orders_items_id >=", value, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdLessThan(String value) {
            addCriterion("orders_items_id <", value, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdLessThanOrEqualTo(String value) {
            addCriterion("orders_items_id <=", value, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdLike(String value) {
            addCriterion("orders_items_id like", value, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdNotLike(String value) {
            addCriterion("orders_items_id not like", value, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdIn(List<String> values) {
            addCriterion("orders_items_id in", values, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdNotIn(List<String> values) {
            addCriterion("orders_items_id not in", values, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdBetween(String value1, String value2) {
            addCriterion("orders_items_id between", value1, value2, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andOrdersItemsIdNotBetween(String value1, String value2) {
            addCriterion("orders_items_id not between", value1, value2, "ordersItemsId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andReturnPriceIsNull() {
            addCriterion("return_price is null");
            return (Criteria) this;
        }

        public Criteria andReturnPriceIsNotNull() {
            addCriterion("return_price is not null");
            return (Criteria) this;
        }

        public Criteria andReturnPriceEqualTo(BigDecimal value) {
            addCriterion("return_price =", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceNotEqualTo(BigDecimal value) {
            addCriterion("return_price <>", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceGreaterThan(BigDecimal value) {
            addCriterion("return_price >", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_price >=", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceLessThan(BigDecimal value) {
            addCriterion("return_price <", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_price <=", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceIn(List<BigDecimal> values) {
            addCriterion("return_price in", values, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceNotIn(List<BigDecimal> values) {
            addCriterion("return_price not in", values, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_price between", value1, value2, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_price not between", value1, value2, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdIsNull() {
            addCriterion("company_address_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdIsNotNull() {
            addCriterion("company_address_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdEqualTo(String value) {
            addCriterion("company_address_id =", value, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdNotEqualTo(String value) {
            addCriterion("company_address_id <>", value, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdGreaterThan(String value) {
            addCriterion("company_address_id >", value, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdGreaterThanOrEqualTo(String value) {
            addCriterion("company_address_id >=", value, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdLessThan(String value) {
            addCriterion("company_address_id <", value, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdLessThanOrEqualTo(String value) {
            addCriterion("company_address_id <=", value, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdLike(String value) {
            addCriterion("company_address_id like", value, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdNotLike(String value) {
            addCriterion("company_address_id not like", value, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdIn(List<String> values) {
            addCriterion("company_address_id in", values, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdNotIn(List<String> values) {
            addCriterion("company_address_id not in", values, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdBetween(String value1, String value2) {
            addCriterion("company_address_id between", value1, value2, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIdNotBetween(String value1, String value2) {
            addCriterion("company_address_id not between", value1, value2, "companyAddressId");
            return (Criteria) this;
        }

        public Criteria andReturnReasonIsNull() {
            addCriterion("return_reason is null");
            return (Criteria) this;
        }

        public Criteria andReturnReasonIsNotNull() {
            addCriterion("return_reason is not null");
            return (Criteria) this;
        }

        public Criteria andReturnReasonEqualTo(String value) {
            addCriterion("return_reason =", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonNotEqualTo(String value) {
            addCriterion("return_reason <>", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonGreaterThan(String value) {
            addCriterion("return_reason >", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonGreaterThanOrEqualTo(String value) {
            addCriterion("return_reason >=", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonLessThan(String value) {
            addCriterion("return_reason <", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonLessThanOrEqualTo(String value) {
            addCriterion("return_reason <=", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonLike(String value) {
            addCriterion("return_reason like", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonNotLike(String value) {
            addCriterion("return_reason not like", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonIn(List<String> values) {
            addCriterion("return_reason in", values, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonNotIn(List<String> values) {
            addCriterion("return_reason not in", values, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonBetween(String value1, String value2) {
            addCriterion("return_reason between", value1, value2, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonNotBetween(String value1, String value2) {
            addCriterion("return_reason not between", value1, value2, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnPicIsNull() {
            addCriterion("return_pic is null");
            return (Criteria) this;
        }

        public Criteria andReturnPicIsNotNull() {
            addCriterion("return_pic is not null");
            return (Criteria) this;
        }

        public Criteria andReturnPicEqualTo(String value) {
            addCriterion("return_pic =", value, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnPicNotEqualTo(String value) {
            addCriterion("return_pic <>", value, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnPicGreaterThan(String value) {
            addCriterion("return_pic >", value, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnPicGreaterThanOrEqualTo(String value) {
            addCriterion("return_pic >=", value, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnPicLessThan(String value) {
            addCriterion("return_pic <", value, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnPicLessThanOrEqualTo(String value) {
            addCriterion("return_pic <=", value, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnPicLike(String value) {
            addCriterion("return_pic like", value, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnPicNotLike(String value) {
            addCriterion("return_pic not like", value, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnPicIn(List<String> values) {
            addCriterion("return_pic in", values, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnPicNotIn(List<String> values) {
            addCriterion("return_pic not in", values, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnPicBetween(String value1, String value2) {
            addCriterion("return_pic between", value1, value2, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnPicNotBetween(String value1, String value2) {
            addCriterion("return_pic not between", value1, value2, "returnPic");
            return (Criteria) this;
        }

        public Criteria andReturnStatusIsNull() {
            addCriterion("return_status is null");
            return (Criteria) this;
        }

        public Criteria andReturnStatusIsNotNull() {
            addCriterion("return_status is not null");
            return (Criteria) this;
        }

        public Criteria andReturnStatusEqualTo(String value) {
            addCriterion("return_status =", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusNotEqualTo(String value) {
            addCriterion("return_status <>", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusGreaterThan(String value) {
            addCriterion("return_status >", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusGreaterThanOrEqualTo(String value) {
            addCriterion("return_status >=", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusLessThan(String value) {
            addCriterion("return_status <", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusLessThanOrEqualTo(String value) {
            addCriterion("return_status <=", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusLike(String value) {
            addCriterion("return_status like", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusNotLike(String value) {
            addCriterion("return_status not like", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusIn(List<String> values) {
            addCriterion("return_status in", values, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusNotIn(List<String> values) {
            addCriterion("return_status not in", values, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusBetween(String value1, String value2) {
            addCriterion("return_status between", value1, value2, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusNotBetween(String value1, String value2) {
            addCriterion("return_status not between", value1, value2, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnAmountIsNull() {
            addCriterion("return_amount is null");
            return (Criteria) this;
        }

        public Criteria andReturnAmountIsNotNull() {
            addCriterion("return_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReturnAmountEqualTo(Integer value) {
            addCriterion("return_amount =", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotEqualTo(Integer value) {
            addCriterion("return_amount <>", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountGreaterThan(Integer value) {
            addCriterion("return_amount >", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_amount >=", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountLessThan(Integer value) {
            addCriterion("return_amount <", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountLessThanOrEqualTo(Integer value) {
            addCriterion("return_amount <=", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountIn(List<Integer> values) {
            addCriterion("return_amount in", values, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotIn(List<Integer> values) {
            addCriterion("return_amount not in", values, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountBetween(Integer value1, Integer value2) {
            addCriterion("return_amount between", value1, value2, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("return_amount not between", value1, value2, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNull() {
            addCriterion("handle_time is null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNotNull() {
            addCriterion("handle_time is not null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeEqualTo(Date value) {
            addCriterion("handle_time =", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotEqualTo(Date value) {
            addCriterion("handle_time <>", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThan(Date value) {
            addCriterion("handle_time >", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("handle_time >=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThan(Date value) {
            addCriterion("handle_time <", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThanOrEqualTo(Date value) {
            addCriterion("handle_time <=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIn(List<Date> values) {
            addCriterion("handle_time in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotIn(List<Date> values) {
            addCriterion("handle_time not in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeBetween(Date value1, Date value2) {
            addCriterion("handle_time between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotBetween(Date value1, Date value2) {
            addCriterion("handle_time not between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNull() {
            addCriterion("delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNotNull() {
            addCriterion("delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeEqualTo(Date value) {
            addCriterion("delivery_time =", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotEqualTo(Date value) {
            addCriterion("delivery_time <>", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThan(Date value) {
            addCriterion("delivery_time >", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_time >=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThan(Date value) {
            addCriterion("delivery_time <", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("delivery_time <=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIn(List<Date> values) {
            addCriterion("delivery_time in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotIn(List<Date> values) {
            addCriterion("delivery_time not in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeBetween(Date value1, Date value2) {
            addCriterion("delivery_time between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("delivery_time not between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNull() {
            addCriterion("receive_time is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNotNull() {
            addCriterion("receive_time is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeEqualTo(Date value) {
            addCriterion("receive_time =", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotEqualTo(Date value) {
            addCriterion("receive_time <>", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThan(Date value) {
            addCriterion("receive_time >", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("receive_time >=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThan(Date value) {
            addCriterion("receive_time <", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("receive_time <=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIn(List<Date> values) {
            addCriterion("receive_time in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotIn(List<Date> values) {
            addCriterion("receive_time not in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeBetween(Date value1, Date value2) {
            addCriterion("receive_time between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("receive_time not between", value1, value2, "receiveTime");
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