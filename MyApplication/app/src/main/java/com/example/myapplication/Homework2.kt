package com.example.myapplication

// 3.1

open class NhanVien {
    var ma_nv: String = ""
    var ho_ten: String = ""
    var ngay_sinh: String = ""
    var dia_chi: String = ""

    constructor()

    open fun nhapThongTin() {
        println("Nhập mã nhân viên: ")
        ma_nv = readLine()!!
        println("Nhập họ tên: ")
        ho_ten = readLine()!!
        println("Nhập ngày sinh: ")
        ngay_sinh = readLine()!!
        println("Nhập địa chỉ: ")
        dia_chi = readLine()!!
    }

    open fun xuatThongTin() {
        println("Thông tin nhân viên:")
        println("Mã nhân viên: $ma_nv")
        println("Họ tên: $ho_ten")
        println("Ngày sinh: $ngay_sinh")
        println("Địa chỉ: $dia_chi")
    }

    open fun tinhLuong(): Double {
        return 0.0
    }
}

class NVSanXuat : NhanVien {
    var so_san_pham: Int

    constructor() : super() {
        this.so_san_pham = 0
    }

    constructor(ma_nv: String, ho_ten: String, ngay_sinh: String, dia_chi: String, so_san_pham: Int) :
            super() {
        this.ma_nv = ma_nv
        this.ho_ten = ho_ten
        this.ngay_sinh = ngay_sinh
        this.dia_chi = dia_chi
        this.so_san_pham = so_san_pham
    }

    override fun nhapThongTin() {
        println("\nNhập NV San Xuat:")
        super.nhapThongTin()
        println("Nhập số sản phẩm: ")
        so_san_pham = readLine()!!.toInt()
    }

    override fun xuatThongTin() {
        super.xuatThongTin()
        println("Số sản phẩm: $so_san_pham")
    }

    override fun tinhLuong(): Double {
        return so_san_pham * 20000.0
    }
}

class NVCongNhat : NhanVien {
    var so_ngay: Int

    constructor() : super() {
        this.so_ngay = 0
    }

    constructor(ma_nv: String, ho_ten: String, ngay_sinh: String, dia_chi: String, so_ngay: Int) :
            super() {
        this.ma_nv = ma_nv
        this.ho_ten = ho_ten
        this.ngay_sinh = ngay_sinh
        this.dia_chi = dia_chi
        this.so_ngay = so_ngay
    }

    override fun nhapThongTin() {
        println("\nNhập NV Cong Nhat:")
        super.nhapThongTin()
        println("Nhập số ngày làm việc: ")
        so_ngay = readLine()!!.toInt()
    }

    override fun xuatThongTin() {
        super.xuatThongTin()
        println("Số ngày làm việc: $so_ngay")
    }

    override fun tinhLuong(): Double {
        return so_ngay * 300000.0
    }
}


// 3.2
open class DocGia {
    var maDocGia: String = ""
    var hoTen: String = ""
    var ngayHetHan: String = ""
    var gioiTinh: String = ""

    constructor()

    constructor(maDocGia: String, hoTen: String, ngayHetHan: String, gioiTinh: String) {
        this.maDocGia = maDocGia
        this.hoTen = hoTen
        this.ngayHetHan = ngayHetHan
        this.gioiTinh = gioiTinh
    }

    open fun nhapThongTin() {
        print("Mã độc giả: ")
        maDocGia = readLine() ?: ""
        print("Họ tên: ")
        hoTen = readLine() ?: ""
        print("Ngày hết hạn: ")
        ngayHetHan = readLine() ?: ""
        print("Giới tính: ")
        gioiTinh = readLine() ?: ""
    }

    open fun xuatThongTin() {
        println("\nThông tin độc giả:")
        println("Mã độc giả: $maDocGia")
        println("Họ tên: $hoTen")
        println("Ngày hết hạn: $ngayHetHan")
        println("Giới tính: $gioiTinh")
    }

    open fun tinhLePhi(): Double {
        return 0.0
    }
}

class DocGiaThuong : DocGia {

    var soSachMuonTrongThang: Int = 0

    constructor() : super()

    constructor(maDocGia: String, hoTen: String, ngayHetHan: String, gioiTinh: String, soSachMuonTrongThang: Int)
            : super(maDocGia, hoTen, ngayHetHan, gioiTinh) {
        this.soSachMuonTrongThang = soSachMuonTrongThang
    }

    override fun nhapThongTin() {
        println("\nNhập thông tin độc giả thường:")
        super.nhapThongTin()
        print("\nNhập số sách mượn trong tháng: ")
        soSachMuonTrongThang = (readLine() ?: "").toIntOrNull() ?: 0
    }

    override fun tinhLePhi(): Double {
        return soSachMuonTrongThang * 5000.0
    }
}

class DocGiaVIP : DocGia {

    constructor() : super()

    constructor(maDocGia: String, hoTen: String, ngayHetHan: String, gioiTinh: String)
            : super(maDocGia, hoTen, ngayHetHan, gioiTinh)

    override fun nhapThongTin() {
        println("\nNhập thông tin độc giả VIP:")
        super.nhapThongTin()
    }

    override fun tinhLePhi(): Double {
        return 50000.0
    }
}

fun main() {
    println("BÀI 3.1")

    val nvSanXuat = NVSanXuat()
    nvSanXuat.nhapThongTin()
    nvSanXuat.xuatThongTin()
    println("Lương của NV San Xuat: ${nvSanXuat.tinhLuong()} VND")

    val nvCongNhat = NVCongNhat()
    nvCongNhat.nhapThongTin()
    nvCongNhat.xuatThongTin()
    println("Lương của NV Cong Nhat: ${nvCongNhat.tinhLuong()} VND")

    println("BÀI 3.2")
    val docGiaThuong = DocGiaThuong()
    docGiaThuong.nhapThongTin()
    docGiaThuong.xuatThongTin()
    println("Lệ phí độc giả thường: ${docGiaThuong.tinhLePhi()} VND")

    val docGiaVIP = DocGiaVIP()
    docGiaVIP.nhapThongTin()
    docGiaVIP.xuatThongTin()
    println("Lệ phí độc giả VIP: ${docGiaVIP.tinhLePhi()} VND")
}
