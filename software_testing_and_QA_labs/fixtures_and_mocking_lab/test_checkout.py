# test_checkout.py
import pytest
from unittest.mock import patch
from checkout_system import calculate_order_total, process_payment_transaction


@pytest.fixture
def standard_shopping_cart():
    """Provides a baseline sample shopping cart dataset."""
    return [
    {"item_name": "Wireless Mouse", "price": 15000, "quantity": 1},
    {"item_name": "USB-C Cable", "price": 5000, "quantity": 2}
    ]
def test_calculate_order_total_with_fixture(standard_shopping_cart):
    # Subtotal calculation: (15000 * 1) + (5000 * 2) = 25000 RWF
    # Total with 15% Tax: 25000 + (25000 * 0.15) = 28750 RWF
    expected_total = 28750.0
    actual_total = calculate_order_total(standard_shopping_cart)
    assert actual_total == expected_total

@patch('checkout_system.requests.post')
def test_process_payment_success(mock_post):
    # Arrange: Force mock to return status 200
    mock_post.return_value.status_code = 200
    # Act
    result = process_payment_transaction(account_id="ACC_4509", amount=28750)
    # Assert
    assert result == "TRANSACTION_SUCCESS"
    mock_post.assert_called_once()

@patch('checkout_system.requests.post')
def test_process_payment_failure(mock_post):
    # Arrange: Force mock to return status 500
    mock_post.return_value.status_code = 500
    # Act
    result = process_payment_transaction(account_id="ACC_4509", amount=28750)
    # Assert
    assert result == "TRANSACTION_FAILED"