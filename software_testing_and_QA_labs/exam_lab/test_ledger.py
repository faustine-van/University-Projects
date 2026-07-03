import pytest
from falco_ledger import apply_ledger_payout


@pytest.mark.parametrize("out_bound", [-1, 0, 10001])
def test_ledger_out_bound(out_bound):
    with pytest.raises(ValueError):
        apply_ledger_payout(account_balance=10000, payout_value=out_bound, high_tier_multiplier=False)
        f"Ledger amount out of bounds"
    

def test_insufficent_funds_out_of_bounds():
    with pytest.raises(ValueError):
        result = apply_ledger_payout(account_balance=100, payout_value=10001, high_tier_multiplier=False)
        assert result == "ERROR_INSUFFICIENT_LEDGER_FUNDS"

def test_insufficent_funds():
    result = apply_ledger_payout(account_balance=100, payout_value=1000, high_tier_multiplier=False)
    assert result == "ERROR_INSUFFICIENT_LEDGER_FUNDS"

def test_ledger_payout_success():
    result = apply_ledger_payout(account_balance=10000, payout_value=10000, high_tier_multiplier=False)
    assert result["status"] == "APPROVED"  
    assert result["new_balance"] == 0
    assert result["payout_disbursed"] == 10000

def test_ledger_final_payout_():
    result = apply_ledger_payout(account_balance=10000, payout_value=1000, high_tier_multiplier=True)
    assert result["status"] == "APPROVED"  
    assert result["new_balance"] == 9000
    assert result["payout_disbursed"] == 1100


