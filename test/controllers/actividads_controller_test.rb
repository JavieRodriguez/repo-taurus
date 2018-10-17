require 'test_helper'

class ActividadsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @actividad = actividads(:one)
  end

  test "should get index" do
    get actividads_url
    assert_response :success
  end

  test "should get new" do
    get new_actividad_url
    assert_response :success
  end

  test "should create actividad" do
    assert_difference('Actividad.count') do
      post actividads_url, params: { actividad: { descripcion: @actividad.descripcion, etapa_id: @actividad.etapa_id } }
    end

    assert_redirected_to actividad_url(Actividad.last)
  end

  test "should show actividad" do
    get actividad_url(@actividad)
    assert_response :success
  end

  test "should get edit" do
    get edit_actividad_url(@actividad)
    assert_response :success
  end

  test "should update actividad" do
    patch actividad_url(@actividad), params: { actividad: { descripcion: @actividad.descripcion, etapa_id: @actividad.etapa_id } }
    assert_redirected_to actividad_url(@actividad)
  end

  test "should destroy actividad" do
    assert_difference('Actividad.count', -1) do
      delete actividad_url(@actividad)
    end

    assert_redirected_to actividads_url
  end
end
