package com.example.demo.Entity

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name="admin_account")
data class AdminAccount(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,

        @Column(name = "real_name", nullable = false, length = 50)
        var realName: String?,

        @Column(name = "admin_name", nullable = false, length = 50)
        var adminName: String?,

        @Column(name = "mobile", nullable = false, length = 20)
        var mobile: String?,
        var password: String?,
        var admin_role_id: String?,
        var created_at: Timestamp?,
        var updated_at: Timestamp
) {

}