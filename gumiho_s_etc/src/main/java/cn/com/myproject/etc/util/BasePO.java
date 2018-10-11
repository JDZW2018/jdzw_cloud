package cn.com.myproject.etc.util;

/**
 * @author zyh
 * @description:
 * @createtime 2018/5/3 0003
 */
public class BasePO {


    private Integer id;// 主键ID

    private Long createTime;// 创建时间

    private Short status;// 状态

    private Integer version;// 版本号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}