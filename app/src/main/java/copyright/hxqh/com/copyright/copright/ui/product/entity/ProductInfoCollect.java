package copyright.hxqh.com.copyright.copright.ui.product.entity;

import java.io.Serializable;

/**
 * Created by zzw on 2018/9/27.
 */

public class ProductInfoCollect implements Serializable {
        private String id;
        private String price;
        private String lifetime;
        private String lifetimeto;
        private String collectionstatus;
        private int serialVersionUID;
        private String productname;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setPrice(String price) {
            this.price = price;
        }
        public String getPrice() {
            return price;
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

        public void setCollectionstatus(String collectionstatus) {
            this.collectionstatus = collectionstatus;
        }
        public String getCollectionstatus() {
            return collectionstatus;
        }

        public void setSerialVersionUID(int serialVersionUID) {
            this.serialVersionUID = serialVersionUID;
        }
        public int getSerialVersionUID() {
            return serialVersionUID;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }
        public String getProductname() {
            return productname;
        }
}
