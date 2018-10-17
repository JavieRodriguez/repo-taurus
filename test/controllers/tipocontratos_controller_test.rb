require 'test_helper'

class TipocontratosControllerTest < ActionDispatch::IntegrationTest
  setup do
    @tipocontrato = tipocontratos(:one)
  end

  test "should get index" do
    get tipocontratos_url
    assert_response :success
  end

  test "should get new" do
    get new_tipocontrato_url
    assert_response :success
  end

  test "should create tipocontrato" do
    assert_difference('Tipocontrato.count') do
      post tipocontratos_url, params: { tipocontrato: { tipo: @tipocontrato.tipo } }
    end

    assert_redirected_to tipocontrato_url(Tipocontrato.last)
  end

  test "should show tipocontrato" do
    get tipocontrato_url(@tipocontrato)
    assert_response :success
  end

  test "should get edit" do
    get edit_tipocontrato_url(@tipocontrato)
    assert_response :success
  end

  test "should update tipocontrato" do
    patch tipocontrato_url(@tipocontrato), params: { tipocontrato: { tipo: @tipocontrato.tipo } }
    assert_redirected_to tipocontrato_url(@tipocontrato)
  end

  test "should destroy tipocontrato" do
    assert_difference('Tipocontrato.count', -1) do
      delete tipocontrato_url(@tipocontrato)
    end

    assert_redirected_to tipocontratos_url
  end
end
