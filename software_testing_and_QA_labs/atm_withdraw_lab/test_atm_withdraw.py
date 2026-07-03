from atm_withdraw import atm_withdraw
import pytest

@pytest.mark.parametrize(
    "balance, amount, withdrawn_today, expected_result",
    [(6000, 4999, 0, "Invalid amount"),
     (5000, 5000, 0, "Take your cash"),
     (5000, 5001, 0, "Invalid amount"),
     (20000, 49999, 55000, "Invalid amount"),
     (20000, 50000, 55000, "Exceeded daily limit"),
     (20000, 50001, 55000, "Invalid amount"),
     (4999, 10000, 0, "Insufficient balance"),
     (5000, 10000, 0, "Insufficient balance"),
     (5001, 10000, 0, "Insufficient balance"),
     (10000, 0, 0, "Invalid amount"),
     (10000, -5000, 0, "Invalid amount")   
]
)

def test_atm_withdraw(balance, amount, withdrawn_today, expected_result):
    res = atm_withdraw(balance, amount, withdrawn_today)
    assert res == expected_result
