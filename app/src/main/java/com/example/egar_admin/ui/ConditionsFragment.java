package com.example.egar_admin.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar_admin.R;
import com.example.egar_admin.databinding.FragmentConditionsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConditionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConditionsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentConditionsBinding binding;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConditionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConditionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConditionsFragment newInstance(String param1, String param2) {
        ConditionsFragment fragment = new ConditionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentConditionsBinding.inflate(inflater);
        binding.tvConditions.setText(text());
        return binding.getRoot();
    }
    private String text(){
        String text= "الشروط العامة\n" +
                "أولا: أسس التأجير:\n" +
                "1 – الحد الادني للايجارهو يوم واحد (أربع وعشرون ساعة).\n" +
                "2 – مدة العقد قابلة للتمديد بموافقة المؤجر والمستأجر على أن يحدد في العقد الإجراءات اللازمة لذلك.\n" +
                "3- يتعهد المستأجر بأنة إذا أدلي بمعلومات غير صحيحة وقت الاستئجار يكون للمؤجر الحق باعتبار السيارة مسروقة وتبليغ الجهات الأمنية.\n" +
                "4- تحسب قيمة ساعات التأخير عن الموعد المحدد لإعادة السيارة بحد أقصي 4 ساعات وفق المعادلة الآتية:\n" +
                "(قيمة الإيجار اليومي ×عدد ساعات التأخير) ÷24×2 = تكلفة قيمة ساعات التأخير\n" +
                "وأي جزء من الساعة تحسب ساعة كاملة.\n" +
                "5 – في حالة عدم طلب تمديد العقد من قبل المستأجر أو عدم موافقة المؤجر على التمديد فان المستأجر يتحمل تكاليف إضافية عن المدة الزائدة الواردة في العقد وحتى إعادة السيارة إلي المؤجر أو استرجاعها من قبلة وبما يعادل 100% (مائة بالمائة) من قيمة التأجير اليومي إذا تجاوز التأخير أربعة ساعات إضافة لتكاليف التأجير المتفق عليها في العقد بما في ذلك تغطية تكاليف الحوادث والمخالفات المرورية وأي تلفيات.\n" +
                "6- في حالة تأخر تسليم السيارة لمدة تزيد عن (24) ساعة عن الموعد المحدد فانه يحق للشركة إخطار الجهات المعنية لاتخاذ اللازم كما يحق للشركة في حالة مخالفة شروط العقد سحب السيارة من خلال الجهات المختصة.\n" +
                "7- لا يجوز إجراء أي تعديل من قبل المستأجر أو المؤجر على العقد بعد توقيعه إلا بموافقة واعتماد كلا الطرفين.\n" +
                "\n" +
                "ثانيا: التزامات المؤجر:\n" +
                "يقر ويتعهد المؤجر بالالتزام بالآتي:\n" +
                "1- صلاحية وسلامة السيارة للاستعمال.\n" +
                "2- في حالة ظهور أي خلل فني في السيارة ليس بسبب تقصير أو إهمال من قبل المستأجر فيتم استبدالها بسيارة من ذات الفئة وفي حال عدم توفرها يتم استبدالها بسيارة من فئة أعلي مع عدم تحميل المستأجر أية تكاليف إضافة وإلا فيتم استبدالها بسيارة من فئة اقل وفقا للتعرفة المعلنة.\n" +
                "3- استلام السيارة التي يقع عليها حادث فور الإبلاغ به مع اعتبار تاريخ ووقت الإبلاغ بالحادث هو نهاية عقد التأجير، مالم تحجز السيارة من قبل السلطات، فإن المستأجر يتحمل أجرة السيارة حتى حصول الاستلام الفعلي لها.\n" +
                "4-الاكتفاء بصورة رخصة القيادة سارية المفعول للمواطن السعودي لاشتمالها على كافة البيانات وصورة الجواز أو الإقامة ورخصة القيادة بالنسبة للأجانب.\n" ;
        return text;

    }
}