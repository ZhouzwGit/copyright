package copyright.hxqh.com.copyright.copright.ui.author.entity;

import java.io.Serializable;

/**
 * Created by zzw on 2018/9/28.
 */

public class RighterResRel implements Serializable {
        private String resourceno;
        private String status;
        private String resgainway;
        private String resourcename;
        private String resourcekind;
        public void setResourceno(String resourceno) {
            this.resourceno = resourceno;
        }
        public String getResourceno() {
            return resourceno;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

        public void setResgainway(String resgainway) {
            this.resgainway = resgainway;
        }
        public String getResgainway() {
            return resgainway;
        }

        public void setResourcename(String resourcename) {
            this.resourcename = resourcename;
        }
        public String getResourcename() {
            return resourcename;
        }

        public void setResourcekind(String resourcekind) {
            this.resourcekind = resourcekind;
        }
        public String getResourcekind() {
            return resourcekind;
        }
}
