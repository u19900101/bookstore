package cn.itcast.goods.ssm.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderitemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderitemExample() {
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

        public Criteria andOrderitemidIsNull() {
            addCriterion("orderItemId is null");
            return (Criteria) this;
        }

        public Criteria andOrderitemidIsNotNull() {
            addCriterion("orderItemId is not null");
            return (Criteria) this;
        }

        public Criteria andOrderitemidEqualTo(String value) {
            addCriterion("orderItemId =", value, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andOrderitemidNotEqualTo(String value) {
            addCriterion("orderItemId <>", value, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andOrderitemidGreaterThan(String value) {
            addCriterion("orderItemId >", value, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andOrderitemidGreaterThanOrEqualTo(String value) {
            addCriterion("orderItemId >=", value, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andOrderitemidLessThan(String value) {
            addCriterion("orderItemId <", value, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andOrderitemidLessThanOrEqualTo(String value) {
            addCriterion("orderItemId <=", value, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andOrderitemidLike(String value) {
            addCriterion("orderItemId like", value, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andOrderitemidNotLike(String value) {
            addCriterion("orderItemId not like", value, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andOrderitemidIn(List<String> values) {
            addCriterion("orderItemId in", values, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andOrderitemidNotIn(List<String> values) {
            addCriterion("orderItemId not in", values, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andOrderitemidBetween(String value1, String value2) {
            addCriterion("orderItemId between", value1, value2, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andOrderitemidNotBetween(String value1, String value2) {
            addCriterion("orderItemId not between", value1, value2, "orderitemid");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andSubtotalIsNull() {
            addCriterion("subtotal is null");
            return (Criteria) this;
        }

        public Criteria andSubtotalIsNotNull() {
            addCriterion("subtotal is not null");
            return (Criteria) this;
        }

        public Criteria andSubtotalEqualTo(BigDecimal value) {
            addCriterion("subtotal =", value, "subtotal");
            return (Criteria) this;
        }

        public Criteria andSubtotalNotEqualTo(BigDecimal value) {
            addCriterion("subtotal <>", value, "subtotal");
            return (Criteria) this;
        }

        public Criteria andSubtotalGreaterThan(BigDecimal value) {
            addCriterion("subtotal >", value, "subtotal");
            return (Criteria) this;
        }

        public Criteria andSubtotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("subtotal >=", value, "subtotal");
            return (Criteria) this;
        }

        public Criteria andSubtotalLessThan(BigDecimal value) {
            addCriterion("subtotal <", value, "subtotal");
            return (Criteria) this;
        }

        public Criteria andSubtotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("subtotal <=", value, "subtotal");
            return (Criteria) this;
        }

        public Criteria andSubtotalIn(List<BigDecimal> values) {
            addCriterion("subtotal in", values, "subtotal");
            return (Criteria) this;
        }

        public Criteria andSubtotalNotIn(List<BigDecimal> values) {
            addCriterion("subtotal not in", values, "subtotal");
            return (Criteria) this;
        }

        public Criteria andSubtotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("subtotal between", value1, value2, "subtotal");
            return (Criteria) this;
        }

        public Criteria andSubtotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("subtotal not between", value1, value2, "subtotal");
            return (Criteria) this;
        }

        public Criteria andBidIsNull() {
            addCriterion("bid is null");
            return (Criteria) this;
        }

        public Criteria andBidIsNotNull() {
            addCriterion("bid is not null");
            return (Criteria) this;
        }

        public Criteria andBidEqualTo(String value) {
            addCriterion("bid =", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotEqualTo(String value) {
            addCriterion("bid <>", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThan(String value) {
            addCriterion("bid >", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThanOrEqualTo(String value) {
            addCriterion("bid >=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThan(String value) {
            addCriterion("bid <", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThanOrEqualTo(String value) {
            addCriterion("bid <=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLike(String value) {
            addCriterion("bid like", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotLike(String value) {
            addCriterion("bid not like", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidIn(List<String> values) {
            addCriterion("bid in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotIn(List<String> values) {
            addCriterion("bid not in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidBetween(String value1, String value2) {
            addCriterion("bid between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotBetween(String value1, String value2) {
            addCriterion("bid not between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andBnameIsNull() {
            addCriterion("bname is null");
            return (Criteria) this;
        }

        public Criteria andBnameIsNotNull() {
            addCriterion("bname is not null");
            return (Criteria) this;
        }

        public Criteria andBnameEqualTo(String value) {
            addCriterion("bname =", value, "bname");
            return (Criteria) this;
        }

        public Criteria andBnameNotEqualTo(String value) {
            addCriterion("bname <>", value, "bname");
            return (Criteria) this;
        }

        public Criteria andBnameGreaterThan(String value) {
            addCriterion("bname >", value, "bname");
            return (Criteria) this;
        }

        public Criteria andBnameGreaterThanOrEqualTo(String value) {
            addCriterion("bname >=", value, "bname");
            return (Criteria) this;
        }

        public Criteria andBnameLessThan(String value) {
            addCriterion("bname <", value, "bname");
            return (Criteria) this;
        }

        public Criteria andBnameLessThanOrEqualTo(String value) {
            addCriterion("bname <=", value, "bname");
            return (Criteria) this;
        }

        public Criteria andBnameLike(String value) {
            addCriterion("bname like", value, "bname");
            return (Criteria) this;
        }

        public Criteria andBnameNotLike(String value) {
            addCriterion("bname not like", value, "bname");
            return (Criteria) this;
        }

        public Criteria andBnameIn(List<String> values) {
            addCriterion("bname in", values, "bname");
            return (Criteria) this;
        }

        public Criteria andBnameNotIn(List<String> values) {
            addCriterion("bname not in", values, "bname");
            return (Criteria) this;
        }

        public Criteria andBnameBetween(String value1, String value2) {
            addCriterion("bname between", value1, value2, "bname");
            return (Criteria) this;
        }

        public Criteria andBnameNotBetween(String value1, String value2) {
            addCriterion("bname not between", value1, value2, "bname");
            return (Criteria) this;
        }

        public Criteria andCurrpriceIsNull() {
            addCriterion("currPrice is null");
            return (Criteria) this;
        }

        public Criteria andCurrpriceIsNotNull() {
            addCriterion("currPrice is not null");
            return (Criteria) this;
        }

        public Criteria andCurrpriceEqualTo(BigDecimal value) {
            addCriterion("currPrice =", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceNotEqualTo(BigDecimal value) {
            addCriterion("currPrice <>", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceGreaterThan(BigDecimal value) {
            addCriterion("currPrice >", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("currPrice >=", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceLessThan(BigDecimal value) {
            addCriterion("currPrice <", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("currPrice <=", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceIn(List<BigDecimal> values) {
            addCriterion("currPrice in", values, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceNotIn(List<BigDecimal> values) {
            addCriterion("currPrice not in", values, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("currPrice between", value1, value2, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("currPrice not between", value1, value2, "currprice");
            return (Criteria) this;
        }

        public Criteria andImageBIsNull() {
            addCriterion("image_b is null");
            return (Criteria) this;
        }

        public Criteria andImageBIsNotNull() {
            addCriterion("image_b is not null");
            return (Criteria) this;
        }

        public Criteria andImageBEqualTo(String value) {
            addCriterion("image_b =", value, "imageB");
            return (Criteria) this;
        }

        public Criteria andImageBNotEqualTo(String value) {
            addCriterion("image_b <>", value, "imageB");
            return (Criteria) this;
        }

        public Criteria andImageBGreaterThan(String value) {
            addCriterion("image_b >", value, "imageB");
            return (Criteria) this;
        }

        public Criteria andImageBGreaterThanOrEqualTo(String value) {
            addCriterion("image_b >=", value, "imageB");
            return (Criteria) this;
        }

        public Criteria andImageBLessThan(String value) {
            addCriterion("image_b <", value, "imageB");
            return (Criteria) this;
        }

        public Criteria andImageBLessThanOrEqualTo(String value) {
            addCriterion("image_b <=", value, "imageB");
            return (Criteria) this;
        }

        public Criteria andImageBLike(String value) {
            addCriterion("image_b like", value, "imageB");
            return (Criteria) this;
        }

        public Criteria andImageBNotLike(String value) {
            addCriterion("image_b not like", value, "imageB");
            return (Criteria) this;
        }

        public Criteria andImageBIn(List<String> values) {
            addCriterion("image_b in", values, "imageB");
            return (Criteria) this;
        }

        public Criteria andImageBNotIn(List<String> values) {
            addCriterion("image_b not in", values, "imageB");
            return (Criteria) this;
        }

        public Criteria andImageBBetween(String value1, String value2) {
            addCriterion("image_b between", value1, value2, "imageB");
            return (Criteria) this;
        }

        public Criteria andImageBNotBetween(String value1, String value2) {
            addCriterion("image_b not between", value1, value2, "imageB");
            return (Criteria) this;
        }

        public Criteria andOidIsNull() {
            addCriterion("oid is null");
            return (Criteria) this;
        }

        public Criteria andOidIsNotNull() {
            addCriterion("oid is not null");
            return (Criteria) this;
        }

        public Criteria andOidEqualTo(String value) {
            addCriterion("oid =", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotEqualTo(String value) {
            addCriterion("oid <>", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThan(String value) {
            addCriterion("oid >", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThanOrEqualTo(String value) {
            addCriterion("oid >=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThan(String value) {
            addCriterion("oid <", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThanOrEqualTo(String value) {
            addCriterion("oid <=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLike(String value) {
            addCriterion("oid like", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotLike(String value) {
            addCriterion("oid not like", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidIn(List<String> values) {
            addCriterion("oid in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotIn(List<String> values) {
            addCriterion("oid not in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidBetween(String value1, String value2) {
            addCriterion("oid between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotBetween(String value1, String value2) {
            addCriterion("oid not between", value1, value2, "oid");
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