require "application_system_test_case"

class ProcesosTest < ApplicationSystemTestCase
  setup do
    @proceso = procesos(:one)
  end

  test "visiting the index" do
    visit procesos_url
    assert_selector "h1", text: "Procesos"
  end

  test "creating a Proceso" do
    visit procesos_url
    click_on "New Proceso"

    fill_in "Modalidad", with: @proceso.modalidad_id
    fill_in "Numero", with: @proceso.numero
    fill_in "Objeto", with: @proceso.objeto
    fill_in "Palabraclave", with: @proceso.palabraclave
    click_on "Create Proceso"

    assert_text "Proceso was successfully created"
    click_on "Back"
  end

  test "updating a Proceso" do
    visit procesos_url
    click_on "Edit", match: :first

    fill_in "Modalidad", with: @proceso.modalidad_id
    fill_in "Numero", with: @proceso.numero
    fill_in "Objeto", with: @proceso.objeto
    fill_in "Palabraclave", with: @proceso.palabraclave
    click_on "Update Proceso"

    assert_text "Proceso was successfully updated"
    click_on "Back"
  end

  test "destroying a Proceso" do
    visit procesos_url
    page.accept_confirm do
      click_on "Destroy", match: :first
    end

    assert_text "Proceso was successfully destroyed"
  end
end
