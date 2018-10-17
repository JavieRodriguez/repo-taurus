class TipocontratosController < ApplicationController
  before_action :set_tipocontrato, only: [:show, :edit, :update, :destroy]

  # GET /tipocontratos
  # GET /tipocontratos.json
  def index
    @tipocontratos = Tipocontrato.all
  end

  # GET /tipocontratos/1
  # GET /tipocontratos/1.json
  def show
  end

  # GET /tipocontratos/new
  def new
    @tipocontrato = Tipocontrato.new
  end

  # GET /tipocontratos/1/edit
  def edit
  end

  # POST /tipocontratos
  # POST /tipocontratos.json
  def create
    @tipocontrato = Tipocontrato.new(tipocontrato_params)

    respond_to do |format|
      if @tipocontrato.save
        format.html { redirect_to @tipocontrato, notice: 'Tipocontrato was successfully created.' }
        format.json { render :show, status: :created, location: @tipocontrato }
      else
        format.html { render :new }
        format.json { render json: @tipocontrato.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /tipocontratos/1
  # PATCH/PUT /tipocontratos/1.json
  def update
    respond_to do |format|
      if @tipocontrato.update(tipocontrato_params)
        format.html { redirect_to @tipocontrato, notice: 'Tipocontrato was successfully updated.' }
        format.json { render :show, status: :ok, location: @tipocontrato }
      else
        format.html { render :edit }
        format.json { render json: @tipocontrato.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /tipocontratos/1
  # DELETE /tipocontratos/1.json
  def destroy
    @tipocontrato.destroy
    respond_to do |format|
      format.html { redirect_to tipocontratos_url, notice: 'Tipocontrato was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_tipocontrato
      @tipocontrato = Tipocontrato.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def tipocontrato_params
      params.require(:tipocontrato).permit(:tipo)
    end
end
