module NativeFortran
    implicit none
contains
    subroutine evaluate(vars, objs, constrs) bind(C)
        !DEC$ ATTRIBUTES DLLEXPORT :: evaluate
        use, intrinsic :: iso_fortran_env, only: DP => real64
        real(DP), dimension(11), intent(in) :: vars
        real(DP), dimension(2), intent(out) :: objs
        real(DP), dimension(0), intent(out) :: constrs

        integer :: nvars = 11, nobjs = 2, nconstrs = 0
        integer :: i, j, k
        real(DP) :: g,  PI = 3.14159265358979323846

        k = 10
        g = 0.0

        do i=nvars-k, nvars
            g = g + (vars(i) - 0.5) ** 2
        end do

        do i=1, nobjs
		    objs(i) = 1.0 + g

            do j=1, nobjs-i-1
			    objs(i) = objs(i) * cos(0.5*PI*vars(j))
            end do

		    if (i > 1) then
			    objs(i) = objs(i) * sin(0.5*PI*vars(nobjs-i-1))
            end if
        end do
    end subroutine
end module
