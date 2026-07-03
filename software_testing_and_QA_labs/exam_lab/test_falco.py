from falco_refill import process_refill_transaction
import pytest

def test_transaction_sucess():
    result = process_refill_transaction(100000, 100, False)
    assert result == {'status': 'SUCCESS', 'remaining_balance': 99900, 'delivered_value': 100}

def test_transaction_maximum_exact():
    result = process_refill_transaction(100000, 5000, False)
    assert result == {'status': 'SUCCESS', 'remaining_balance': 95000, 'delivered_value': 5000}

def test_transaction_vip_bonus():
    result = process_refill_transaction(100000, 100, True)
    assert result['delivered_value'] == 105.0


@pytest.mark.parametrize("invalid_amount", [0, -1, 5001])
def test_transaction_limit_breach(invalid_amount):
    with pytest.raises(ValueError, match="limit breach") as exc_info:
        process_refill_transaction(100000, invalid_amount, False)
    assert str(exc_info.value) == "Transaction limit breach"
    assert "ValueError" in str(exc_info.type)
    # assert str(exc_info.type) == "<class 'ValueError'>"

# def test_transaction_limit_zero():
#     with pytest.raises(ValueError):
#         process_refill_transaction(100000, 0, False)
#         f"Transaction limit breach"


def test_transaction_insufficient_funds():
    result = process_refill_transaction(0, 200, False)
    assert result == "DENIED_INSUFFICIENT_FUNDS"

