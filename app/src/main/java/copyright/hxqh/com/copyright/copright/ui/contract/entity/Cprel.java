package copyright.hxqh.com.copyright.copright.ui.contract.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzw on 2018/9/29.
 */

public class Cprel implements Serializable {

        private String productusage;
        private String lifetime;
        private String lifetimeto;
        private String status;
        private String productno;
        private List<ConMatterDivide> conMatterDivideList;
        private List<ConRightDivide> conRightDivideList;
        private String productname;
        public void setProductusage(String productusage) {
            this.productusage = productusage;
        }
        public String getProductusage() {
            return productusage;
        }

        public void setLifetime(String lifetime) {
            this.lifetime = lifetime;
        }
        public String getLifetime() {
            return lifetime;
        }

        public void setLifetimeto(String lifetimeto) {
            this.lifetimeto = lifetimeto;
        }
        public String getLifetimeto() {
            return lifetimeto;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

        public void setProductno(String productno) {
            this.productno = productno;
        }
        public String getProductno() {
            return productno;
        }

        public void setConMatterDivideList(List<ConMatterDivide> conMatterDivideList) {
            this.conMatterDivideList = conMatterDivideList;
        }
        public List<ConMatterDivide> getConMatterDivideList() {
            return conMatterDivideList;
        }

        public void setConRightDivideList(List<ConRightDivide> conRightDivideList) {
            this.conRightDivideList = conRightDivideList;
        }
        public List<ConRightDivide> getConRightDivideList() {
            return conRightDivideList;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }
        public String getProductname() {
            return productname;
        }

}
