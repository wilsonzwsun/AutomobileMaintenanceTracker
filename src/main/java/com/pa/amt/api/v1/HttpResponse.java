package com.pa.amt.api.v1;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponse implements Serializable {
        private long code;
        private String message;
        private Long total;
        private Object data;
        private List<Object> table;
        private String requestid;
        public static final long CODE_SUCCESS = 200;
        public static final long CODE_ERROR = 500;
        public static final long CODE_VALIDATE_FAILED = 404;
        public static final long CODE_UNAUTHORIZED = 401;
        public static final long CODE_FORBIDDEN = 403;
        public HttpResponse() {
            this(200, (String) null);    }
        public HttpResponse(long code, String message) {
            this.code = CODE_SUCCESS;
            this.code = code;
            this.message = message;
            //this.requestid = UUID.randomUUID().toString().replaceAll("-", "");
        }
        public static HttpResponse error(String message) {
            return new HttpResponse(500, message);
        }
        public HttpResponse setData(Object data) {
            this.data = data;
            return this;
        }
        public HttpResponse data(Object data) {
            return this.setData(data);
        }
        public HttpResponse addListItem(Object item) {
            if (this.table == null) {
                this.table = new ArrayList<>();
            }
            this.table.add(item);

            return this;
        }
        public HttpResponse setTotal(Long total) {
            this.total = total;
            return this;
        }
        public HttpResponse setTotal(Integer total) {
            this.total = (long) total;
            return this;
        }
        public static HttpResponse ok() {
            return new HttpResponse();
        }
        public HttpResponse set(String field, String value) {
            if (this.data == null || !(this.data instanceof Map)) {
                this.data = new HashMap();
            }
            ((Map) this.data).put(field, value);
            return this;
        }
        public long getCode() {
            return this.code;
        }
        public HttpResponse setCode(long code) {
            this.code = code;
            return this;
        }    public String getMessage() {
            return this.message;
        }
        public HttpResponse setMessage(String message) {
            this.message = message;
            return this;
        }
        public Long getTotal() {
            return this.total == null && this.table != null ?     Long.valueOf(String.valueOf(this.table.size())) : this.total;
        }
        public Object getData() {
            return this.data;
        }
        public List<Object> getTable() {
            return this.table;
        }
        public HttpResponse setTable(List table) {
            this.table = table;
            return this;
        }
        public HttpResponse table(List table) {
            return this.setTable(table);
        }
        public String getRequestid() {
            return this.requestid;
        }
        public HttpResponse setRequestid(String requestid) {
            this.requestid = requestid;
            return this;
        }
}
