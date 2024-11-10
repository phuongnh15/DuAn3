CREATE DATABASE BanLapTopDell;
go
USE BanLapTopDell;

CREATE TABLE Cpu (
    macpu VARCHAR(10) PRIMARY KEY NOT NULL,
    trangthai TINYINT DEFAULT 1
);


CREATE TABLE Gpu (
    magpu VARCHAR(10) PRIMARY KEY NOT NULL,
    trangthai TINYINT DEFAULT 1
);

CREATE TABLE Ram (
    maram VARCHAR(10) PRIMARY KEY NOT NULL,
    trangthai TINYINT DEFAULT 1
);

CREATE TABLE MauSac (
    mamau VARCHAR(10) PRIMARY KEY NOT NULL,
    tenmau VARCHAR(10) NOT NULL,
    trangthai TINYINT DEFAULT 1
);

CREATE TABLE DungLuong (
    maDL VARCHAR(20) PRIMARY KEY NOT NULL,
    dungLuong VARCHAR(20) NOT NULL,
    trangthai TINYINT DEFAULT 1
);

CREATE TABLE SanPham (
    masp VARCHAR(20) PRIMARY KEY NOT NULL,
	tenSP nvarchar(30) not null,
    soluongtonkho INT NOT NULL,
    gia DECIMAL(10, 2) NOT NULL,
    cpu VARCHAR(10) NOT NULL,
    gpu VARCHAR(10) NOT NULL,
    ram VARCHAR(10) NOT NULL,
    mausac VARCHAR(10),
    dungLuong VARCHAR(20),
    FOREIGN KEY (cpu) REFERENCES Cpu(macpu),
    FOREIGN KEY (gpu) REFERENCES Gpu(magpu),
    FOREIGN KEY (ram) REFERENCES Ram(maram),
    FOREIGN KEY (mausac) REFERENCES MauSac(mamau),
    FOREIGN KEY (dungLuong) REFERENCES DungLuong(maDL)
);

CREATE TABLE Imei (
    imei VARCHAR(30) PRIMARY KEY,
    trangthai TINYINT DEFAULT 1,
    masp VARCHAR(20),
    FOREIGN KEY (masp) REFERENCES SanPham(masp)
);

create table NhanVien (
	id_nhanVien varchar(15) not null primary key,
	ten nvarchar(50),
	soDienThoai varchar(10),
	email varchar(50),
	gioiTinh bit,
	taiKhoan varchar(20),
	matKhau varchar(20)
)

CREATE TABLE KhachHang (
    makhachhang VARCHAR(15) PRIMARY KEY NOT NULL,
    ten NVARCHAR(30) NOT NULL,
    sodienthoai VARCHAR(15) UNIQUE,
    email VARCHAR(30),
    diachi NVARCHAR(50),
    gioiTinh BIT
);

CREATE TABLE Voucher (
    maVoucher VARCHAR(15) PRIMARY KEY NOT NULL,
    moTa VARCHAR(30) NOT NULL,
    giamGia FLOAT,
    giamGiaToiDa DECIMAL(10, 2),
    hinhThucGiamGia INT NOT NULL,
    ngayBD DATE,
    ngayKT DATE,
    dieuKienApDung VARCHAR(50)
);

CREATE TABLE HoaDon (
    mahoadon VARCHAR(10) PRIMARY KEY,
    tongtienBanDau DECIMAL(15, 2),
    makhachhang VARCHAR(15),
    maVoucher VARCHAR(15),
    tongkhuyenmai DECIMAL(15, 2) DEFAULT 0.00,
    ngaythanhtoan DATE,
    trangthai TINYINT DEFAULT 1,
    tongtienSauKM DECIMAL(15, 2),
	id_nhanvien varchar(15) not null,
    FOREIGN KEY (makhachhang) REFERENCES KhachHang(makhachhang),
    FOREIGN KEY (maVoucher) REFERENCES Voucher(maVoucher),
	FOREIGN KEY (id_nhanvien) REFERENCES NhanVien(id_nhanVien)
);

CREATE TABLE HoaDonChiTiet (
    mahoadonct INT PRIMARY KEY IDENTITY,
    soluong INT NOT NULL,
    dongia DECIMAL(10, 2) NOT NULL,
    mahoadon VARCHAR(10),
    imei VARCHAR(30),
    masanpham VARCHAR(20),
    FOREIGN KEY (mahoadon) REFERENCES HoaDon(mahoadon),
    FOREIGN KEY (imei) REFERENCES Imei(imei),
    FOREIGN KEY (masanpham) REFERENCES SanPham(masp)
);

CREATE TABLE ImeiDaBan (
    id INT PRIMARY KEY IDENTITY,
    maimei VARCHAR(30) NOT NULL,
    masp VARCHAR(20),
    mahoadonct INT,
    FOREIGN KEY (maimei) REFERENCES Imei(imei),
    FOREIGN KEY (masp) REFERENCES SanPham(masp),
    FOREIGN KEY (mahoadonct) REFERENCES HoaDonChiTiet(mahoadonct)
);

CREATE TABLE ThongKeSPBanChay (
    stt INT PRIMARY KEY IDENTITY,
    masp VARCHAR(20),
    soluongbanra INT DEFAULT 0,
    tongdoanhthu DECIMAL(15, 2) DEFAULT 0.00,
    thang INT,
    nam INT,
    FOREIGN KEY (masp) REFERENCES SanPham(masp)
);

CREATE TABLE ThongKeDoanhThu (
    mathongke INT PRIMARY KEY IDENTITY,
    thang INT,
    ngay INT,
    nam INT,
    tongdoanhthu DECIMAL(15, 2) DEFAULT 0.00,
    tonghoadon INT DEFAULT 0,
    tongspbanra INT DEFAULT 0
);


CREATE TABLE GioHangTamThoi (
    magioHangTamthoi INT PRIMARY KEY IDENTITY,
    soluong INT NOT NULL,
    dongia DECIMAL(10, 2) NOT NULL,
    mahoadon VARCHAR(10),
    imei VARCHAR(30),
    masanpham VARCHAR(20),
    FOREIGN KEY (mahoadon) REFERENCES HoaDon(mahoadon),
    FOREIGN KEY (imei) REFERENCES Imei(imei),
    FOREIGN KEY (masanpham) REFERENCES SanPham(masp)
);

-- Dữ liệu mẫu cho bảng Cpu
INSERT INTO Cpu (macpu, trangthai) VALUES 
('INTEL_I5', 1),
('INTEL_I7', 1),
('INTEL_I9', 1),
('XEON_E5', 1),
('RYZEN_7', 1);

-- Dữ liệu mẫu cho bảng Gpu
INSERT INTO Gpu (magpu, trangthai) VALUES 
('GTX_1050', 1),
('GTX_1660', 1),
('RTX_2060', 1),
('RTX_3080', 1),
('RX_580', 1);

-- Dữ liệu mẫu cho bảng Ram
INSERT INTO Ram (maram, trangthai) VALUES 
('RAM_8GB', 1),
('RAM_16GB', 1),
('RAM_32GB', 1),
('RAM_64GB', 1),
('RAM_128GB', 1);

-- Dữ liệu mẫu cho bảng MauSac
INSERT INTO MauSac (mamau, tenmau, trangthai) VALUES 
('MS01', N'Đen', 1),
('MS02', N'Trắng', 1),
('MS03', N'Xám', 1),
('MS04', N'Xanh', 1),
('MS05', N'Đỏ', 1);

-- Dữ liệu mẫu cho bảng DungLuong
INSERT INTO DungLuong (maDL, dungLuong, trangthai) VALUES 
('DL128GB', '128GB', 1),
('DL256GB', '256GB', 1),
('DL512GB', '512GB', 1),
('DL1TB', '1TB', 1),
('DL2TB', '2TB', 1);

-- Dữ liệu mẫu cho bảng SanPham
INSERT INTO SanPham (masp, tenSP, soluongtonkho, gia, cpu, gpu, ram, mausac, dungLuong) VALUES 
('SP001', N'Dell Inspiron 3501', 10, 15000000, 'INTEL_I5', 'GTX_1050', 'RAM_8GB', 'MS01', 'DL256GB'),
('SP002', N'Dell XPS 13', 5, 25000000, 'INTEL_I7', 'GTX_1660', 'RAM_16GB', 'MS02', 'DL512GB'),
('SP003', N'Dell Alienware M15', 3, 35000000, 'INTEL_I9', 'RTX_2060', 'RAM_32GB', 'MS03', 'DL1TB'),
('SP004', N'Dell Precision 7740', 2, 45000000, 'XEON_E5', 'RTX_3080', 'RAM_64GB', 'MS04', 'DL2TB'),
('SP005', N'Dell G5 15', 8, 20000000, 'RYZEN_7', 'RX_580', 'RAM_16GB', 'MS05', 'DL512GB');


-- Dữ liệu mẫu cho bảng Imei
INSERT INTO Imei (imei, trangthai, masp) VALUES
('IMEI00001', 1, 'SP001'),
('IMEI00002', 1, 'SP002'),
('IMEI00003', 1, 'SP003'),
('IMEI00004', 1, 'SP004'),
('IMEI00005', 1, 'SP005');

-- Dữ liệu mẫu cho bảng KhachHang
INSERT INTO KhachHang (makhachhang, ten, sodienthoai, email, diachi, gioiTinh) VALUES
('KH001', N'Nguyen Van A', '0912345678', 'a@example.com', N'Hà Nội', 1),
('KH002', N'Tran Thi B', '0987654321', 'b@example.com', N'Hồ Chí Minh', 0),
('KH003', N'Le Van C', '0934567890', 'c@example.com', N'Đà Nẵng', 1),
('KH004', N'Pham Thi D', '0945678901', 'd@example.com', N'Bình Dương', 0),
('KH005', N'Vo Van E', '0956789012', 'e@example.com', N'Cần Thơ', 1);

-- Dữ liệu mẫu cho bảng Voucher
INSERT INTO Voucher (maVoucher, moTa, giamGia, giamGiaToiDa, hinhThucGiamGia, ngayBD, ngayKT, dieuKienApDung) VALUES
('VOUCHER10', N'Giảm 10%', 10, 500000, 1, '2024-01-01', '2024-12-31', N'Áp dụng cho đơn hàng trên 5 triệu'),
('VOUCHER20', N'Giảm 20%', 20, 1000000, 1, '2024-01-01', '2024-06-30', N'Áp dụng cho đơn hàng trên 10 triệu'),
('VOUCHER50', N'Giảm 50%', 50, 2000000, 1, '2024-02-01', '2024-04-30', N'Áp dụng cho đơn hàng trên 20 triệu'),
('FREESHIP', N'Miễn phí vận chuyển', NULL, NULL, 2, '2024-01-01', '2024-12-31', N'Không yêu cầu điều kiện'),
('VOUCHER5', N'Giảm 5%', 5, 300000, 1, '2024-01-01', '2024-12-31', N'Áp dụng cho đơn hàng trên 3 triệu');


INSERT INTO NhanVien (id_nhanVien, ten, soDienThoai, email, gioiTinh, taiKhoan, matKhau)
VALUES 
    ('NV001', N'Nguyễn Văn A', '0901234567', 'nva@example.com', 1, 'nguyenvana', 'password1'),
    ('NV002', N'Trần Thị B', '0912345678', 'ttb@example.com', 0, 'tranthib', 'password2'),
    ('NV003', N'Phạm Văn C', '0923456789', 'pvc@example.com', 1, 'phamvanc', 'password3'),
    ('NV004', N'Lê Thị D', '0934567890', 'ltd@example.com', 0, 'lethid', 'password4'),
    ('NV005', N'Hoàng Văn E', '0945678901', 'hve@example.com', 1, 'hoangvane', 'password5');

-- Dữ liệu mẫu cho bảng HoaDon
INSERT INTO HoaDon (mahoadon, tongtienBanDau, makhachhang, maVoucher, tongkhuyenmai, ngaythanhtoan, trangthai, tongtienSauKM, id_nhanvien) VALUES
    ('HD001', 15000000, 'KH001', 'VOUCHER10', 500000, '2024-01-15', 1, 14500000, 'NV001'),
    ('HD002', 25000000, 'KH002', 'VOUCHER20', 1000000, '2024-01-20', 1, 24000000, 'NV002'),
    ('HD003', 35000000, 'KH003', 'VOUCHER50', 2000000, '2024-02-05', 1, 33000000, 'NV003'),
    ('HD004', 45000000, 'KH004', NULL, 0, '2024-03-10', 1, 45000000, 'NV004'),
    ('HD005', 20000000, 'KH005', 'VOUCHER5', 300000, '2024-03-15', 1, 19700000, 'NV005');

-- Dữ liệu mẫu cho bảng HoaDonChiTiet
INSERT INTO HoaDonChiTiet (soluong, dongia, mahoadon, imei, masanpham) VALUES
(1, 15000000, 'HD001', 'IMEI00001', 'SP001'),
(1, 25000000, 'HD002', 'IMEI00002', 'SP002'),
(1, 35000000, 'HD003', 'IMEI00003', 'SP003'),
(1, 45000000, 'HD004', 'IMEI00004', 'SP004'),
(1, 20000000, 'HD005', 'IMEI00005', 'SP005');

-- Dữ liệu mẫu cho bảng ImeiDaBan
INSERT INTO ImeiDaBan (maimei, masp, mahoadonct) VALUES
('IMEI00001', 'SP001', 1),
('IMEI00002', 'SP002', 2),
('IMEI00003', 'SP003', 3),
('IMEI00004', 'SP004', 4),
('IMEI00005', 'SP005', 5);

-- Dữ liệu mẫu cho bảng ThongKeSPBanChay
INSERT INTO ThongKeSPBanChay (masp, soluongbanra, tongdoanhthu, thang, nam) VALUES
('SP001', 10, 150000000, 1, 2024),
('SP002', 5, 125000000, 2, 2024),
('SP003', 3, 105000000, 3, 2024),
('SP004', 2, 90000000, 4, 2024),
('SP005', 8, 160000000, 5, 2024);

-- Dữ liệu mẫu cho bảng ThongKeDoanhThu
INSERT INTO ThongKeDoanhThu (thang, ngay, nam, tongdoanhthu, tonghoadon, tongspbanra) VALUES
(1, 15, 2024, 150000000, 1, 10),
(2, 20, 2024, 125000000, 1, 5),
(3, 5, 2024, 105000000, 1, 3),
(4, 10, 2024, 90000000, 1, 2),
(5, 15, 2024, 160000000, 1, 8);

-- Dữ liệu mẫu bảng NhanVien


-- 1. Lấy danh sách CPU
SELECT * FROM Cpu;

-- 2. Lấy danh sách GPU
SELECT * FROM Gpu;

-- 3. Lấy danh sách RAM
SELECT * FROM Ram;

-- 4. Lấy danh sách Màu sắc
SELECT * FROM MauSac;

-- 5. Lấy danh sách Dung lượng
SELECT * FROM DungLuong;

-- 6. Lấy danh sách Sản phẩm, kèm thông tin CPU, GPU, RAM, màu sắc và dung lượng
SELECT 
    sp.masp, sp.tenSP, sp.soluongtonkho, sp.gia, 
    c.macpu AS CPU, g.magpu AS GPU, r.maram AS RAM, 
    m.tenmau AS MauSac, d.dungLuong
FROM 
    SanPham sp
JOIN 
    Cpu c ON sp.cpu = c.macpu
JOIN 
    Gpu g ON sp.gpu = g.magpu
JOIN 
    Ram r ON sp.ram = r.maram
LEFT JOIN 
    MauSac m ON sp.mausac = m.mamau
LEFT JOIN 
    DungLuong d ON sp.dungLuong = d.maDL;

-- 7. Lấy danh sách Imei của sản phẩm
SELECT * FROM Imei;

-- 8. Lấy danh sách khách hàng
SELECT * FROM KhachHang;

-- 9. Lấy danh sách voucher đang có hiệu lực
SELECT * FROM Voucher WHERE GETDATE() BETWEEN ngayBD AND ngayKT;

-- 10. Lấy danh sách hóa đơn cùng tổng tiền sau khuyến mãi
SELECT 
    hd.mahoadon, hd.tongtienBanDau, hd.tongkhuyenmai, 
    hd.tongtienSauKM, hd.ngaythanhtoan, kh.ten AS TenKhachHang, 
    v.moTa AS MoTaVoucher
FROM 
    HoaDon hd
LEFT JOIN 
    KhachHang kh ON hd.makhachhang = kh.makhachhang
LEFT JOIN 
    Voucher v ON hd.maVoucher = v.maVoucher;

-- 11. Lấy danh sách chi tiết từng hóa đơn
SELECT 
    hdc.mahoadonct, hdc.soluong, hdc.dongia, 
    hdc.mahoadon, hdc.imei, sp.tenSP AS SanPham
FROM 
    HoaDonChiTiet hdc
JOIN 
    SanPham sp ON hdc.masanpham = sp.masp;

-- 12. Lấy danh sách Imei đã bán
SELECT 
    idb.id, idb.maimei, idb.masp, idb.mahoadonct, 
    sp.tenSP AS SanPham
FROM 
    ImeiDaBan idb
JOIN 
    SanPham sp ON idb.masp = sp.masp;

-- 13. Thống kê sản phẩm bán chạy
SELECT 
    tk.stt, tk.masp, sp.tenSP, tk.soluongbanra, 
    tk.tongdoanhthu, tk.thang, tk.nam
FROM 
    ThongKeSPBanChay tk
JOIN 
    SanPham sp ON tk.masp = sp.masp;

-- 14. Thống kê doanh thu hàng tháng
SELECT 
    thang, nam, SUM(tongdoanhthu) AS TongDoanhThu, 
    SUM(tonghoadon) AS TongHoaDon, SUM(tongspbanra) AS TongSPBanRa
FROM 
    ThongKeDoanhThu
GROUP BY 
    thang, nam
ORDER BY 
    nam DESC, thang DESC;

