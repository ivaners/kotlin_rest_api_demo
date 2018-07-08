package com.example.demo.entity

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name="admin_role")
data class AdminRole(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,

        var name: String?,
        var remark: String?,
        var created_at: Timestamp?,
        var updated_at: Timestamp
) {
}