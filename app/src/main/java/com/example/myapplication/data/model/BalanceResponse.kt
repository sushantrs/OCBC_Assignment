import com.google.gson.annotations.SerializedName

data class BalanceResponse(
    @SerializedName("status") var status: String? = null,
    @SerializedName("accountNo") var accountNo: String? = null,
    @SerializedName("balance") var balance: Int? = null
)