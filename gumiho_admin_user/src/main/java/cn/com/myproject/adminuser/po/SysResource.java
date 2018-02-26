package cn.com.myproject.adminuser.po;


import cn.com.myproject.util.BasePO;

import java.util.List;


public class SysResource extends BasePO {
    private String resourceId;
    private String resourceName;
    /**
     * 资源值
     * type=1,url
     * type=2,字符串
     * */
    private String value;

    private Short type;

    private String parentId;

    private String icon;

    private Short menu;

    private Integer seqno;

    private List<SysResource> children;
    private boolean hasChildren;


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Short getMenu() {
        return menu;
    }

    public void setMenu(Short menu) {
        this.menu = menu;
    }

    public Integer getSeqno() {
        return seqno;
    }

    public void setSeqno(Integer seqno) {
        this.seqno = seqno;
    }

    public List<SysResource> getChildren() {
        return children;
    }

    public void setChildren(List<SysResource> children) {
        this.children = children;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean getHasChildren() {
        hasChildren = this.children == null || this.children.isEmpty() ? false
                : true;
        return hasChildren;
    }
}
