package session18;

import java.sql.Date;

public class Product {
    private int maSanPham;
    private String tenSanPham;
    private float giaSanPham;
    private String tieuDeSanPham;
    private Date ngayTao;
    private String danhMuc;
    private int trangThai;

    // Constructor đầy đủ
    public Product(int maSanPham, String tenSanPham, float giaSanPham, String tieuDeSanPham,
                   Date ngayTao, String danhMuc, int trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.tieuDeSanPham = tieuDeSanPham;
        this.ngayTao = ngayTao;
        this.danhMuc = danhMuc;
        this.trangThai = trangThai;
    }

    // Constructor rỗng
    public Product() {}

    // Getter & Setter
    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public float getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(float giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getTieuDeSanPham() {
        return tieuDeSanPham;
    }

    public void setTieuDeSanPham(String tieuDeSanPham) {
        this.tieuDeSanPham = tieuDeSanPham;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    // toString để hiển thị thông tin sản phẩm
    @Override
    public String toString() {
        return "Product{" +
                "maSanPham=" + maSanPham +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", giaSanPham=" + giaSanPham +
                ", tieuDeSanPham='" + tieuDeSanPham + '\'' +
                ", ngayTao=" + ngayTao +
                ", danhMuc='" + danhMuc + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
