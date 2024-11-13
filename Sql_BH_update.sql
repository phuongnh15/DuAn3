use BanLapTopDell
-- Thay doi cua Cong

DECLARE @TriggerName NVARCHAR(128)
DECLARE trigger_cursor CURSOR FOR
SELECT name
FROM sys.triggers
WHERE parent_id = OBJECT_ID('HoaDon');

OPEN trigger_cursor;
FETCH NEXT FROM trigger_cursor INTO @TriggerName;

WHILE @@FETCH_STATUS = 0
BEGIN
    EXEC('DROP TRIGGER ' + @TriggerName);
    FETCH NEXT FROM trigger_cursor INTO @TriggerName;
END;

CLOSE trigger_cursor;
DEALLOCATE trigger_cursor;

CREATE TRIGGER trg_AutoGenerate_MaHoaDon
ON HoaDon
AFTER INSERT
AS
BEGIN
    DECLARE @MaHoaDon NVARCHAR(10);
	DECLARE @MaHoaDonInsert NVARCHAR(10);
	SELECT @MaHoaDonInsert = MaHoaDon
FROM INSERTED;
    DECLARE @MaxMaHoaDon NVARCHAR(10);
    DECLARE @NewMaHoaDon NVARCHAR(10);

    -- Lấy mã hóa đơn lớn nhất hiện tại (ví dụ: HD0010)
    SELECT @MaxMaHoaDon = MAX(MaHoaDon)
    FROM HoaDon
    WHERE MaHoaDon LIKE 'HD%';

    -- Nếu chưa có mã hóa đơn nào, bắt đầu từ HD001
    IF @MaxMaHoaDon IS NULL
    BEGIN
        SET @NewMaHoaDon = 'HD001';
    END
    ELSE
    BEGIN
        -- Tách số phía sau mã hóa đơn và cộng thêm 1
        SET @NewMaHoaDon = 'HD' + RIGHT('000' + CAST(CAST(SUBSTRING(@MaxMaHoaDon, 3, LEN(@MaxMaHoaDon)) AS INT) + 1 AS NVARCHAR(3)), 3);
    END

    -- Cập nhật MaHoaDon cho các bản ghi vừa chèn vào bảng HoaDon
    UPDATE HoaDon
    SET MaHoaDon = @NewMaHoaDon ,trangthai = 0
    FROM HoaDon h
    where mahoadon =@MaHoaDonInsert

END;
select * from HoaDon
select *from HoaDonChiTiet
ALTER TABLE Voucher
ADD PhanTramGiamGia INT;

DROP TRIGGER trg_UpdateTrangThaiHoaDon;
--xoatrigger

--cap nhat trang thai hoa don tu gio hang
CREATE TRIGGER trg_UpdateTrangThaiHoaDon
ON HoaDonChiTiet
AFTER INSERT
AS
BEGIN
    DECLARE @maHoaDon varchar(10) ;

    -- Lấy mã hóa đơn từ bản ghi vừa chèn
    SELECT @maHoaDon = maHoaDon FROM inserted;

    
        -- Cập nhật trạng thái của hóa đơn sang "Đã hoàn tất"
        UPDATE HoaDon
        SET trangThai = 1
        WHERE maHoaDon = @maHoaDon;
    END

select * from ImeiDaBan

drop table ImeiDaBan
CREATE TABLE ImeiDaBan (
    id INT PRIMARY KEY IDENTITY,
    maimei VARCHAR(30) NOT NULL,
    masp VARCHAR(20),
   
);


DROP TRIGGER trg_InsertImeiDaBan;
--xoatrigger

CREATE TRIGGER trg_InsertImeiDaBan
ON HoaDonChiTiet
AFTER INSERT
AS
BEGIN
    -- Biến lưu trữ thông tin được chèn vào bảng HoaDonChiTiet
    DECLARE @maimei VARCHAR(30);
    DECLARE @masp VARCHAR(20);
   

    -- Lấy giá trị từ bảng HoaDonChiTiet sau khi chèn
    SELECT 
        @maimei = i.imei,
        @masp = i.masanpham
       
    FROM 
        inserted i
    
    
    -- Thực hiện chèn dữ liệu vào bảng ImeiDaBan
    INSERT INTO ImeiDaBan (maimei, masp)
    VALUES (@maimei, @masp);
END;



DROP TRIGGER trg_UpdateStockAndDeleteImei;
--xoatrigger
--trừ số lượng tồn kho
CREATE TRIGGER trg_UpdateStockAndDeleteImei
ON HoaDonChiTiet
AFTER INSERT
AS
BEGIN
    -- Biến lưu trữ thông tin cần thiết
    DECLARE @masp VARCHAR(20);
    DECLARE @imei VARCHAR(30);

    -- Lấy thông tin từ bảng 'inserted' (bảng ảo chứa bản ghi mới được chèn vào HoaDonChiTiet)
    SELECT 
        @masp = masanpham,
        @imei = imei
    FROM 
        inserted;

    -- Trừ đi 1 số lượng tồn kho của sản phẩm trong bảng SanPham
    UPDATE SanPham
    SET soluongtonkho = soluongtonkho - 1
    WHERE masp = @masp AND soluongtonkho > 0;

END;
select * from Imei
--Cộng dồn số lượng tồm kho 
CREATE TRIGGER trg_UpdateSoLuongTonKho
ON Imei
AFTER INSERT
AS
BEGIN
    -- Tăng số lượng tồn kho cho mỗi sản phẩm
    UPDATE SanPham
    SET soluongtonkho = soluongtonkho + (
        SELECT COUNT(*)
        FROM Inserted
        WHERE Inserted.masp = SanPham.masp
    )
    WHERE masp IN (SELECT DISTINCT masp FROM Inserted);
END;




select Imei,trangthai,masp from Imei where masp ='sp001'

delete from GioHangTamThoi
SELECT imei 
FROM Imei i
WHERE i.masp =  'SP002'
AND NOT EXISTS (
    SELECT 1 
    FROM ImeiDaBan idb 
    WHERE idb.maimei = i.imei
);


select * from Imei
select *from KhachHang
select * from HoaDon
select * from GioHangTamThoi
delete from GioHangTamThoi where imei = ?
-- IMEI cho sản phẩm SP001
INSERT INTO Imei (imei, trangthai, masp) VALUES
('IMEI00101', 1, 'SP001'),
('IMEI00102', 1, 'SP001'),
('IMEI00103', 1, 'SP001'),
('IMEI00104', 1, 'SP001'),
('IMEI00105', 1, 'SP001');

-- IMEI cho sản phẩm SP002
INSERT INTO Imei (imei, trangthai, masp) VALUES
('IMEI00201', 1, 'SP002'),
('IMEI00202', 1, 'SP002'),
('IMEI00203', 1, 'SP002'),
('IMEI00204', 1, 'SP002'),
('IMEI00205', 1, 'SP002');

-- IMEI cho sản phẩm SP003
INSERT INTO Imei (imei, trangthai, masp) VALUES
('IMEI00301', 1, 'SP003'),
('IMEI00302', 1, 'SP003'),
('IMEI00303', 1, 'SP003'),
('IMEI00304', 1, 'SP003'),
('IMEI00305', 1, 'SP003');

-- IMEI cho sản phẩm SP004
INSERT INTO Imei (imei, trangthai, masp) VALUES
('IMEI00401', 1, 'SP004'),
('IMEI00402', 1, 'SP004'),
('IMEI00403', 1, 'SP004'),
('IMEI00404', 1, 'SP004'),
('IMEI00405', 1, 'SP004');

-- IMEI cho sản phẩm SP005
INSERT INTO Imei (imei, trangthai, masp) VALUES
('IMEI00501', 1, 'SP005'),
('IMEI00502', 1, 'SP005'),
('IMEI00503', 1, 'SP005'),
('IMEI00504', 1, 'SP005'),
('IMEI00505', 1, 'SP005');


-- Thay doi cua Phuong
select mahoadon, kh.makhachhang, kh.ten, kh.sodienthoai, id_nhanvien, ngaythanhtoan, tongtienBanDau, tongkhuyenmai, maVoucher, tongtienSauKM, trangthai from HoaDon hd
join KhachHang kh on kh.makhachhang = hd.makhachhang where trangthai = ?

delete from HoaDonChiTiet where mahoadon = ?
delete from HoaDon where mahoadon = ?

-- Thay doi cua Long


-- Thay doi cua Hien
ALTER TABLE NhanVien
ADD trangThai BIT;

-- Thay doi cua Linh