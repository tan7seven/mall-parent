package com.mall.mallmbg.model;

import java.util.ArrayList;
import java.util.List;

public class SystemButtonAuthorityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SystemButtonAuthorityExample() {
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

        public Criteria andButtonAuthorityIdIsNull() {
            addCriterion("button_authority_id is null");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdIsNotNull() {
            addCriterion("button_authority_id is not null");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdEqualTo(String value) {
            addCriterion("button_authority_id =", value, "buttonAuthorityId");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdNotEqualTo(String value) {
            addCriterion("button_authority_id <>", value, "buttonAuthorityId");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdGreaterThan(String value) {
            addCriterion("button_authority_id >", value, "buttonAuthorityId");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdGreaterThanOrEqualTo(String value) {
            addCriterion("button_authority_id >=", value, "buttonAuthorityId");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdLessThan(String value) {
            addCriterion("button_authority_id <", value, "buttonAuthorityId");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdLessThanOrEqualTo(String value) {
            addCriterion("button_authority_id <=", value, "buttonAuthorityId");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdLike(String value) {
            addCriterion("button_authority_id like", value, "buttonAuthorityId");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdNotLike(String value) {
            addCriterion("button_authority_id not like", value, "buttonAuthorityId");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdIn(List<String> values) {
            addCriterion("button_authority_id in", values, "buttonAuthorityId");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdNotIn(List<String> values) {
            addCriterion("button_authority_id not in", values, "buttonAuthorityId");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdBetween(String value1, String value2) {
            addCriterion("button_authority_id between", value1, value2, "buttonAuthorityId");
            return (Criteria) this;
        }

        public Criteria andButtonAuthorityIdNotBetween(String value1, String value2) {
            addCriterion("button_authority_id not between", value1, value2, "buttonAuthorityId");
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

        public Criteria andButtonIdIsNull() {
            addCriterion("button_id is null");
            return (Criteria) this;
        }

        public Criteria andButtonIdIsNotNull() {
            addCriterion("button_id is not null");
            return (Criteria) this;
        }

        public Criteria andButtonIdEqualTo(String value) {
            addCriterion("button_id =", value, "buttonId");
            return (Criteria) this;
        }

        public Criteria andButtonIdNotEqualTo(String value) {
            addCriterion("button_id <>", value, "buttonId");
            return (Criteria) this;
        }

        public Criteria andButtonIdGreaterThan(String value) {
            addCriterion("button_id >", value, "buttonId");
            return (Criteria) this;
        }

        public Criteria andButtonIdGreaterThanOrEqualTo(String value) {
            addCriterion("button_id >=", value, "buttonId");
            return (Criteria) this;
        }

        public Criteria andButtonIdLessThan(String value) {
            addCriterion("button_id <", value, "buttonId");
            return (Criteria) this;
        }

        public Criteria andButtonIdLessThanOrEqualTo(String value) {
            addCriterion("button_id <=", value, "buttonId");
            return (Criteria) this;
        }

        public Criteria andButtonIdLike(String value) {
            addCriterion("button_id like", value, "buttonId");
            return (Criteria) this;
        }

        public Criteria andButtonIdNotLike(String value) {
            addCriterion("button_id not like", value, "buttonId");
            return (Criteria) this;
        }

        public Criteria andButtonIdIn(List<String> values) {
            addCriterion("button_id in", values, "buttonId");
            return (Criteria) this;
        }

        public Criteria andButtonIdNotIn(List<String> values) {
            addCriterion("button_id not in", values, "buttonId");
            return (Criteria) this;
        }

        public Criteria andButtonIdBetween(String value1, String value2) {
            addCriterion("button_id between", value1, value2, "buttonId");
            return (Criteria) this;
        }

        public Criteria andButtonIdNotBetween(String value1, String value2) {
            addCriterion("button_id not between", value1, value2, "buttonId");
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