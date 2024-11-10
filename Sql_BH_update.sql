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
INSERT INTO HoaDon (mahoadon,id_nhanvien)
VALUES ('He3','NV005');  -- Chỉ cần id_nhanvien

select *from HoaDon






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


-- Thay doi cua Linh